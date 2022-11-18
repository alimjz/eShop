package com.registration;

import com.shop.core.model.entity.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.registration.repository")
@EntityScan(basePackages = "com.shop.core.entity",basePackageClasses =
        {Order.class, Product.class, Customer.class, Contact.class, Address.class,
        Invoice.class, SalesItem.class, Warehouse.class, User.class})
public class RegistrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(RegistrationApplication.class, args);
    }

}
