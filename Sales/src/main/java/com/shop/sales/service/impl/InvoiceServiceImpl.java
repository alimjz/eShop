package com.shop.sales.service.impl;


import com.shop.core.mapper.EntityFactory;
import com.shop.core.model.dto.InvoiceDto;
import com.shop.core.model.entity.Invoice;
import com.shop.core.model.entity.Product;
import com.shop.sales.repository.OrderRepository;
import com.shop.sales.service.InvoiceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@ConfigurationProperties(prefix = "app.sell")
@Slf4j
public class InvoiceServiceImpl implements InvoiceService {
    private static final Double TAX_RATE = 0.09;
    private final OrderRepository orderRepository;


    @Autowired
    public InvoiceServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Invoice calculateInvoice(List<Product> products, String customerId) {
        double totalPrice = 0;
        double totalTax = 0;
        double discountPercent = calculateDiscount(orderRepository.countOrdersByCustomer_CustomerId(customerId));
        for (Product product :
                products) {
            totalPrice += product.getSellPrice();
            totalTax += (product.getSellPrice() * TAX_RATE);
        }

        InvoiceDto invoiceDto = new InvoiceDto();
        invoiceDto.setBaseFee(totalPrice);
        invoiceDto.setTax(totalTax);
        invoiceDto.setDiscountPercent(discountPercent);
        invoiceDto.setDiscountAmount((totalPrice + totalTax) * discountPercent);
        invoiceDto.setPayAbleAmount(totalPrice + totalTax - invoiceDto.getDiscountAmount());
        log.info(invoiceDto.toString());
        return EntityFactory.convertToInvoiceFactory(invoiceDto);

    }

    @Override
    public Invoice calculateInvoiceForSales(List<Product> products, String customerId) {
        return calculateInvoice(products, customerId);
    }

    @Override
    public Optional<Invoice> queryInvoice(String id) {
        return Optional.empty();
    }

    private Double calculateDiscount(int orderCount) {
        if (orderCount <= 5)
            return 0D;
        else if (orderCount > 6 && orderCount < 11)
            return 0.03D;
        else
            return 0.05D;
    }


}
