package com.shop.sales.repository;

import com.shop.core.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {

    int countOrdersByCustomer_CustomerId(String customerId);
}
