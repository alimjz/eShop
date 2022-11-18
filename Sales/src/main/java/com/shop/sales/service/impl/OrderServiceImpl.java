package com.shop.sales.service.impl;

import com.shop.core.mapper.EntityFactory;
import com.shop.core.model.dto.OrderDto;
import com.shop.core.model.entity.Customer;
import com.shop.core.model.entity.Invoice;
import com.shop.core.model.entity.Order;
import com.shop.core.model.entity.Product;
import com.shop.core.model.enums.BusinessCode;
import com.shop.sales.exception.ProductNotFoundException;
import com.shop.sales.repository.CustomerRepository;
import com.shop.sales.repository.OrderRepository;
import com.shop.sales.service.InvoiceService;
import com.shop.sales.service.OrderService;
import com.shop.sales.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;

import static com.shop.sales.exception.ErrorConstants.CUSTOMER_NOT_FOUND;
import static com.shop.sales.exception.ErrorConstants.PRODUCT_NOT_FOUND;


@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final InvoiceService invoiceService;
    private final CustomerRepository customerRepository;
    private final ProductService productService;


    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, InvoiceService invoiceService
            , CustomerRepository customerRepository, ProductService productService) {
        this.orderRepository = orderRepository;
        this.invoiceService = invoiceService;
        this.customerRepository = customerRepository;
        this.productService = productService;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Order createSalesOrder(OrderDto orderDto) {
        Optional<Customer> customer = customerRepository.findById(orderDto.getCustomerId());
        List<Product> products = productService.findNonZeroQuantsProducts(orderDto.getProductsId());
        Invoice invoice = invoiceService.calculateInvoice(products, orderDto.getCustomerId());
        if (!customer.isPresent()) {
            throw new NotFoundException(CUSTOMER_NOT_FOUND);
        } else if (products.size() != orderDto.getProductsId().size()) {
            throw new ProductNotFoundException(PRODUCT_NOT_FOUND);
        }
        Order order = EntityFactory.orderConvertorFactory(orderDto, products, customer, invoice, BusinessCode.SALE);
        return orderRepository.save(order);
    }

    @Override
    public List<Order> queryAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> queryOrderById(String id) {
        return orderRepository.findById(id);
    }

    @Override
    public int countCustomerOrders(String id) {
        return orderRepository.countOrdersByCustomer_CustomerId(id);
    }
}
