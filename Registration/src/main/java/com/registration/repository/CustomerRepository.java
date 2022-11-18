package com.registration.repository;

import com.shop.core.model.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,String> {
    @Override
    Page<Customer> findAll(Pageable pageable);

    Optional<Customer> findCustomerByNationalId(String id);
}
