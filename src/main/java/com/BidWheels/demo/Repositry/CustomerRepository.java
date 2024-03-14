package com.BidWheels.demo.Repositry;

import com.BidWheels.demo.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByUserId(Long userId);
    // You can add custom query methods if needed
}
