package com.BidWheels.demo.Controller;

import com.BidWheels.demo.Model.UserDetails;
import com.BidWheels.demo.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-details")
public class UserDetailsController {

    @Autowired
    private UserDetailsService userDetailsService;



    @GetMapping("/getdata")
    public ResponseEntity<List<UserDetails>> getAllUserDetails() {
        try {
            List<UserDetails> userDetailsList = userDetailsService.getAllUserDetails();
            return ResponseEntity.ok(userDetailsList);
        } catch (Exception e) {
            // Log the exception or handle it as needed
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Internal Server Error
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getUserDetailsById(@PathVariable Long userId) {
        try {
            UserDetails userDetails = userDetailsService.getUserDetailsById(userId);
            if (userDetails != null) {
                return ResponseEntity.ok(userDetails);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("UserDetails not found with ID: " + userId);
            }
        } catch (Exception e) {
            // Log the exception or handle it as needed
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving user details"); // Internal Server Error
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Object> createUserDetails(@RequestBody UserDetails userDetails) {
        try {
            UserDetails createdUserDetails = userDetailsService.createUserDetails(userDetails);
            return ResponseEntity.ok(createdUserDetails);
        } catch (Exception e) {
            // Log the exception or handle it as needed
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating user details"); // Internal Server Error
        }
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<Object> updateUserDetails(@PathVariable Long userId, @RequestBody UserDetails updatedUserDetails) {
        try {
            UserDetails userDetails = userDetailsService.updateUserDetails(userId, updatedUserDetails);
            if (userDetails != null) {
                return ResponseEntity.ok(userDetails);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("UserDetails not found with ID: " + userId);
            }
        } catch (Exception e) {
            // Log the exception or handle it as needed
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating user details"); // Internal Server Error
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> deleteUserDetails(@PathVariable Long userId) {
        try {
            userDetailsService.deleteUserDetails(userId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            // Log the exception or handle it as needed
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting user details"); // Internal Server Error
        }
    }
}
