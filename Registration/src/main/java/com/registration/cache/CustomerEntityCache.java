package com.registration.cache;

import com.registration.repository.CustomerRepository;
import com.shop.core.model.entity.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Slf4j
@Component
public class CustomerEntityCache {

    private final CustomerRepository repository ;
    public static final Set<String> customerCache = new HashSet<>();

    @Autowired
    private CustomerEntityCache(CustomerRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    private  void cacheInitializer() {
        long start = System.currentTimeMillis();
        List<Customer> customerList = this.repository.findAll();
        log.info("Customer Cache Initialization with " + customerList.size() + " Items.");
        for (Customer customer : customerList) {
            customerCache.add(customer.getNationalId());
        }
        long end = System.currentTimeMillis();
        log.info("Customer Cache Initialization finished in " + (end - start) + " millisecond.");
    }
}
