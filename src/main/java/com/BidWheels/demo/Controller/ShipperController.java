package com.BidWheels.demo.Controller;

import com.BidWheels.demo.Model.Customer;
import com.BidWheels.demo.Model.Shipper;
import com.BidWheels.demo.service.ShipperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shippers")
public class ShipperController {

    @Autowired
    private ShipperService shipperService;

    @GetMapping("/getdata")
    public ResponseEntity<List<Shipper>> getAllShippers() {
        try {
            List<Shipper> shippers = shipperService.getAllShippers();
            return ResponseEntity.ok(shippers);
        } catch (Exception e) {
            // Log the exception or handle it as needed
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Internal Server Error
        }
    }

    @GetMapping("/{shipperId}")
    public ResponseEntity<Object> getShipperById(@PathVariable Long shipperId) {
        try {
            Shipper shipper = shipperService.getShipperById(shipperId);
            if (shipper != null) {
                return ResponseEntity.ok(shipper);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Shipper not found with ID: " + shipperId);
            }
        } catch (Exception e) {
            // Log the exception or handle it as needed
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving shipper"); // Internal Server Error
        }
    }

    // Add other CRUD methods as needed...

    @GetMapping("/details/{userId}")
    public ResponseEntity<Object> getShipperDetailsByUserId(@PathVariable Long userId) {
        try {
            Shipper shipper = shipperService.getShipperDetailsByUserId(userId);
            if (shipper != null) {
                return ResponseEntity.ok(shipper);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Shipper not found for User ID: " + userId);
            }
        } catch (Exception e) {
            // Log the exception or handle it as needed
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving shipper details"); // Internal Server Error
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Long> saveShipper(@RequestBody Shipper shipper) {
        // Log the received customer object and its userId
        System.out.println("Received Customer: " + shipper);
        System.out.println("Received userId: " + shipper.getUserId());

        // Ensure userId is set in the Customer object
        if (shipper.getUserId() == null) {
            return ResponseEntity.badRequest().body(Long.valueOf("userId must be provided"));
        }

        // Call service method to save the customer
        Shipper savedShipper = shipperService.addShipper(shipper);

        // Return the customerId in the response body
        return ResponseEntity.ok(savedShipper.getShipperId());
    }
}
