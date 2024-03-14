package com.BidWheels.demo;

import com.BidWheels.demo.Model.Customer;
import com.BidWheels.demo.Repositry.CustomerRepository;
import com.BidWheels.demo.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @Test
    void getAllCustomers() {
        // Prepare data
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer());
        customers.add(new Customer());

        // Mock repository method
        Mockito.when(customerRepository.findAll()).thenReturn(customers);

        // Call service method
        List<Customer> result = customerService.getAllCustomers();

        // Verify
        assertEquals(customers.size(), result.size());
    }

    @Test
    void getCustomerById_CustomerFound() {
        // Prepare data
        Customer customer = new Customer();
        customer.setCustomerId(1L);

        // Mock repository method
        Mockito.when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        // Call service method
        Customer result = customerService.getCustomerById(1L);

        // Verify
        assertEquals(customer, result);
    }

    @Test
    void getCustomerById_CustomerNotFound() {
        // Mock repository method
        Mockito.when(customerRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Call service method
        Customer result = customerService.getCustomerById(1L);

        // Verify
        assertNull(result);
    }

    @Test
    void getCustomerDetailsByUserId_CustomerFound() {
        // Prepare data
        Customer customer = new Customer();
        customer.setUserId(1L);

        // Mock repository method
        Mockito.when(customerRepository.findByUserId(1L)).thenReturn(Optional.of(customer));

        // Call service method
        Customer result = customerService.getCustomerDetailsByUserId(1L);

        // Verify
        assertEquals(customer, result);
    }

    @Test
    void getCustomerDetailsByUserId_CustomerNotFound() {
        // Mock repository method
        Mockito.when(customerRepository.findByUserId(anyLong())).thenReturn(Optional.empty());

        // Call service method
        Customer result = customerService.getCustomerDetailsByUserId(1L);

        // Verify
        assertNull(result);
    }
}
