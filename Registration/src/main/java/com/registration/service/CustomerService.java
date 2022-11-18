package com.registration.service;

import com.shop.core.model.dto.CustomerDto;
import com.shop.core.model.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CustomerService {
    Customer registerCustomer(CustomerDto customerDto);

    Page<Customer> findAllCustomers(Pageable pageable);

    Optional<Customer> findCustomerById(String id);

    Optional<Customer> findCustomerByCertificate(String id);
}
