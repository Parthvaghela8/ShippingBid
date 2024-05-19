package com.BidWheels.demo.Controller;

import com.BidWheels.demo.Model.Customer;
import com.BidWheels.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/save")
    public ResponseEntity<Long> saveCustomer(@RequestBody Customer customer) {
        // Log the received customer object and its userId
        System.out.println("Received Customer: " + customer);
        System.out.println("Received userId: " + customer.getUserId());

        // Ensure userId is set in the Customer object
        if (customer.getUserId() == null) {
            return ResponseEntity.badRequest().body(Long.valueOf("userId must be provided"));
        }

        // Call service method to save the customer
        Customer savedCustomer = customerService.addCustomer(customer);

        // Return the customerId in the response body
        return ResponseEntity.ok(savedCustomer.getCustomerId());
    }


//    @PostMapping("/add")
//    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
//        System.out.println(customer);
//        Customer savedCustomer = customerService.addCustomer(customer);
//        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
//    }

    @GetMapping("/getdata")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long customerId) {
        Customer customer = customerService.getCustomerById(customerId);

        if (customer != null) {
            return ResponseEntity.ok(customer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Add other CRUD methods as needed...

    @GetMapping("/details/{userId}")
    public ResponseEntity<Customer> getCustomerDetailsByUserId(@PathVariable Long userId) {
        Customer customer = customerService.getCustomerDetailsByUserId(userId);

        if (customer != null) {
            return ResponseEntity.ok(customer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        boolean deleted = customerService.deleteCustomer(id);
        if (deleted) {
            return ResponseEntity.ok("Customer deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
