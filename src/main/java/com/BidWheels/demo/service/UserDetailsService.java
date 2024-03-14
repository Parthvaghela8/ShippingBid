package com.BidWheels.demo.service;

import com.BidWheels.demo.Model.UserDetails;
import com.BidWheels.demo.Repositry.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsService {

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    public List<UserDetails> getAllUserDetails() {
        return userDetailsRepository.findAll();
    }

    public UserDetails getUserDetailsById(Long userId) {
        return userDetailsRepository.findById(userId).orElse(null);
    }

    public UserDetails createUserDetails(UserDetails userDetails) {
        return userDetailsRepository.save(userDetails);
    }

    public UserDetails updateUserDetails(Long userId, UserDetails updatedUserDetails) {
        UserDetails existingUserDetails = userDetailsRepository.findById(userId).orElse(null);

        if (existingUserDetails != null) {
            existingUserDetails.setFirstName(updatedUserDetails.getFirstName());
            existingUserDetails.setLastName(updatedUserDetails.getLastName());
            existingUserDetails.setGender(updatedUserDetails.getGender());
            existingUserDetails.setDateOfBirth(updatedUserDetails.getDateOfBirth());
            existingUserDetails.setContactNumber(updatedUserDetails.getContactNumber());

            return userDetailsRepository.save(existingUserDetails);
        } else {
            return null; // Handle user details not found
        }
    }

    public void deleteUserDetails(Long userId) {
        userDetailsRepository.deleteById(userId);
    }
}
