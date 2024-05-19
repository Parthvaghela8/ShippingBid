package com.BidWheels.demo.service;

import com.BidWheels.demo.Model.Customer;
import com.BidWheels.demo.Model.User;
import com.BidWheels.demo.Repositry.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {


    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository)
    {
        this.customerRepository = customerRepository;
    }

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

    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public boolean deleteCustomer(Long id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            return true;
        }
        return false;
    }

//    public User addUser(User user) {
//        return userRepository.save(user);
//    }

//    public Customer addCustomer(Customer customer) {
//        // You can implement additional validation logic here if needed
//        return customerRepository.save(customer);
//    }
}
