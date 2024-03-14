package com.BidWheels.demo.ControllerTest;

import com.BidWheels.demo.Controller.CustomerController;
import com.BidWheels.demo.Model.Customer;
import com.BidWheels.demo.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    private MockMvc mockMvc;

    @Test
    void getAllCustomers() throws Exception {
        // Prepare data
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer());
        customers.add(new Customer());

        // Mock service method
        when(customerService.getAllCustomers()).thenReturn(customers);

        // Set up MockMvc
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();

        // Perform GET request
        mockMvc.perform(get("/api/customers/getdata"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(customers.size()));
    }

    @Test
    void getCustomerById_CustomerFound() throws Exception {
        // Prepare data
        Customer customer = new Customer();
        customer.setCustomerId(1L);

        // Mock service method
        when(customerService.getCustomerById(1L)).thenReturn(customer);

        // Set up MockMvc
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();

        // Perform GET request
        mockMvc.perform(get("/api/customers/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.customerId").value(1));
    }

    @Test
    void getCustomerById_CustomerNotFound() throws Exception {
        // Mock service method
        when(customerService.getCustomerById(anyLong())).thenReturn(null);

        // Set up MockMvc
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();

        // Perform GET request
        mockMvc.perform(get("/api/customers/45"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getCustomerDetailsByUserId_CustomerFound() throws Exception {
        // Prepare data
        Customer customer = new Customer();
        customer.setCustomerId(1L);

        // Mock service method
        when(customerService.getCustomerDetailsByUserId(1L)).thenReturn(customer);

        // Set up MockMvc
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();

        // Perform GET request
        mockMvc.perform(get("/api/customers/details/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.customerId").value(1));
    }

    @Test
    void getCustomerDetailsByUserId_CustomerNotFound() throws Exception {
        // Mock service method
        when(customerService.getCustomerDetailsByUserId(anyLong())).thenReturn(null);

        // Set up MockMvc
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();

        // Perform GET request
        mockMvc.perform(get("/api/customers/details/45"))
                .andExpect(status().isNotFound());
    }
}
