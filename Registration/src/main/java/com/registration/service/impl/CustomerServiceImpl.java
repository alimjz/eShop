package com.registration.service.impl;

import com.registration.cache.CustomerEntityCache;
import com.registration.exception.CustomerExistException;
import com.registration.exception.RegistrationConstants;
import com.registration.repository.CustomerRepository;
import com.registration.service.CustomerService;
import com.shop.core.mapper.EntityFactory;
import com.shop.core.model.dto.CustomerDto;
import com.shop.core.model.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer registerCustomer(CustomerDto customerDto) {
        Customer customer = EntityFactory.convertToCustomer(customerDto);
        if (!CustomerEntityCache.customerCache.contains(customer.getNationalId())) {
            customer = customerRepository.save(customer);
            CustomerEntityCache.customerCache.add(customer.getNationalId());
            return customer;
        }
        throw new CustomerExistException(RegistrationConstants.DUPLICATE_CUSTOMER_REGISTRATION);

    }

    @Override
    @Transactional(readOnly = true)
    public Page<Customer> findAllCustomers(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    public Optional<Customer> findCustomerById(String id) {
        return customerRepository.findById(id);
    }

    @Override
    public Optional<Customer> findCustomerByCertificate(String id) {
        return customerRepository.findCustomerByNationalId(id);
    }
}
