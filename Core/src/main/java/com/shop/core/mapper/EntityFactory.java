package com.shop.core.mapper;

import com.shop.core.model.dto.*;
import com.shop.core.model.entity.*;
import com.shop.core.model.enums.AddressType;
import com.shop.core.model.enums.BusinessCode;
import com.shop.core.model.enums.OrderStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EntityFactory {
    private EntityFactory() {}
    public static Customer convertToCustomer(CustomerDto customerDto) {
        if (customerDto == null) {
            return null;
        }
        Address address = customerAddressFactory(customerDto);
        Contact contact = contactFactory(customerDto, address);
        Customer customer = new Customer();
        customer.setNationalId(customerDto.getNationalId());
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setBirthDate(customerDto.getBirthDate());
        customer.setBirthCertificateNo(customerDto.getBirthCertificateNo());
        customer.setBirthPlace(customerDto.getBirthPlace());
        customer.setContactInfo(contact);
        return customer;
    }

    private static Contact contactFactory(CustomerDto customerDto, Address address) {
        Contact contact = new Contact();
        contact.setContactType(ContactType.CUSTOMER);
        contact.setEmail(customerDto.getContactInfo().getEmail());
        contact.setPhoneNumber(customerDto.getContactInfo().getPhoneNumber());
        contact.setAddress(address);
        return contact;
    }

    private static Address customerAddressFactory(CustomerDto customerDto) {
        Address address = new Address();
        address.setAddressType(AddressType.CUSTOMER);
        address.setProvince(customerDto.getContactInfo().getAddress().getProvince());
        address.setCity(customerDto.getContactInfo().getAddress().getCity());
        address.setStreet(customerDto.getContactInfo().getAddress().getStreet());
        address.setBuildingNo(customerDto.getContactInfo().getAddress().getBuildingNo());
        address.setPostalCode(customerDto.getContactInfo().getAddress().getPostalCode());
        return address;
    }


    private static Contact userContactFactory(UserDto userDto, Address address) {
        Contact contact = new Contact();
        contact.setContactType(ContactType.USER);
        contact.setEmail(userDto.getContact().getEmail());
        contact.setAddress(address);
        contact.setPhoneNumber(userDto.getContact().getPhoneNumber());
        return contact;
    }

    private static Address userAddressFactory(UserDto userDto) {
        Address address = new Address();
        address.setAddressType(AddressType.USER);
        address.setProvince(userDto.getContact().getAddress().getProvince());
        address.setCity(userDto.getContact().getAddress().getCity());
        address.setStreet(userDto.getContact().getAddress().getStreet());
        address.setBuildingNo(userDto.getContact().getAddress().getBuildingNo());
        address.setPostalCode(userDto.getContact().getAddress().getPostalCode());
        return address;
    }



    public static Order orderConvertorFactory(OrderDto order, List<Product> products, Optional<Customer> customer,
                                   Invoice invoice, BusinessCode businessCode) {
        if (order == null) {
            return null;
        }
        Order orderObj = new Order();
        orderObj.setCustomer(customer.get());
        orderObj.setSalesItem(productToSalesItemFactory(products));
        orderObj.setInvoice(invoice);
        orderObj.setBusinessCode(BusinessCode.SALE);
        orderObj.setStatus(OrderStatus.ACKNOWLEDGE);
        orderObj.setBusinessCode(businessCode);
        return orderObj;
    }
    private static List<SalesItem> productToSalesItemFactory(List<Product> products) {
        List<SalesItem> salesItemList = new ArrayList<>();
        for (Product product : products) {
            SalesItem salesItem = new SalesItem();
            salesItem.setProductId(product.getProdId());
            salesItem.setProdName(product.getProdName());
            salesItem.setSellPrice(product.getSellPrice());
            salesItem.setProdCode(product.getProdCode());
            salesItem.setQuantity(1);
            salesItemList.add(salesItem);
        }
        return salesItemList;
    }


    public static Invoice convertToInvoiceFactory(InvoiceDto invoiceDto) {
        if (invoiceDto == null) {
            return null;
        }

        Invoice invoice = new Invoice();

        invoice.setInvoiceId(invoiceDto.getInvoiceId());
        invoice.setBaseFee(invoiceDto.getBaseFee());
        invoice.setTax(invoiceDto.getTax());
        invoice.setDiscountPercent(invoiceDto.getDiscountPercent());
        invoice.setDiscountAmount(invoiceDto.getDiscountAmount());
        invoice.setPayAbleAmount(invoiceDto.getPayAbleAmount());

        return invoice;
    }


    public InvoiceDto invoiceToDtoMapper(Invoice invoice) {
        if (invoice == null) {
            return null;
        }

        InvoiceDto invoiceDto = new InvoiceDto();

        invoiceDto.setInvoiceId(invoice.getInvoiceId());
        invoiceDto.setBaseFee(invoice.getBaseFee());
        invoiceDto.setTax(invoice.getTax());
        invoiceDto.setDiscountPercent(invoice.getDiscountPercent());
        invoiceDto.setDiscountAmount(invoice.getDiscountAmount());
        invoiceDto.setPayAbleAmount(invoice.getPayAbleAmount());

        return invoiceDto;
    }

    public static Product convertToProductFactory(ProductDto productDto) {
        if (productDto == null) {
            return null;
        }

        Product product = new Product();

        product.setProdId(productDto.getProdId());
        product.setProdCode(productDto.getProdCode());
        product.setProdName(productDto.getProdName());
        product.setBuyPrice(productDto.getBuyPrice());
        product.setSellPrice(productDto.getSellPrice());
        product.setModel(productDto.getModel());
        product.setProdType(productDto.getProdType());
        product.setProdSubType(productDto.getProdSubType());
        product.setQuants(productDto.getQuants());

        return product;
    }

    public static ProductDto convertToProductDtoFactory(Product product) {
        if (product == null) {
            return null;
        }
        ProductDto productDto1 = new ProductDto();
        productDto1.setProdId(product.getProdId());
        productDto1.setProdCode(product.getProdCode());
        productDto1.setProdName(product.getProdName());
        productDto1.setBuyPrice(product.getBuyPrice());
        productDto1.setSellPrice(product.getSellPrice());
        productDto1.setModel(product.getModel());
        productDto1.setProdType(product.getProdType());
        productDto1.setProdSubType(product.getProdSubType());

        return productDto1;
    }
}
