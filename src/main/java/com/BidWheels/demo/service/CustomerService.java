package com.BidWheels.demo.service;

import com.BidWheels.demo.Model.Customer;
import com.BidWheels.demo.Repositry.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long customerId) {
        return customerRepository.findById(customerId).orElse(null);
    }

    // Add other methods as needed...

    public Customer getCustomerDetailsByUserId(Long userId) {
        return (Customer) customerRepository.findByUserId(userId).orElse(null);
    }
}
