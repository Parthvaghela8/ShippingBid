package com.BidWheels.demo;

import com.BidWheels.demo.Model.UserDetails;
import com.BidWheels.demo.Repositry.UserDetailsRepository;
import com.BidWheels.demo.service.UserDetailsService;
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
class UserDetailsServiceTest {

    @Mock
    private UserDetailsRepository userDetailsRepository;

    @InjectMocks
    private UserDetailsService userDetailsService;

    @Test
    void getAllUserDetails() {
        // Prepare data
        List<UserDetails> userDetailsList = new ArrayList<>();
        userDetailsList.add(new UserDetails());
        userDetailsList.add(new UserDetails());

        // Mock repository method
        Mockito.when(userDetailsRepository.findAll()).thenReturn(userDetailsList);

        // Call service method
        List<UserDetails> result = userDetailsService.getAllUserDetails();

        // Verify
        assertEquals(userDetailsList.size(), result.size());
    }

    @Test
    void getUserDetailsById() {
        // Prepare data
        UserDetails userDetails = new UserDetails();
        userDetails.setUserId(1);

        // Mock repository method
        Mockito.when(userDetailsRepository.findById(1L)).thenReturn(Optional.of(userDetails));

        // Call service method
        UserDetails result = userDetailsService.getUserDetailsById(1L);

        // Verify
        assertEquals(userDetails, result);
    }

    @Test
    void createUserDetails() {
        // Prepare data
        UserDetails userDetails = new UserDetails();

        // Mock repository method
        Mockito.when(userDetailsRepository.save(any(UserDetails.class))).thenReturn(userDetails);

        // Call service method
        UserDetails result = userDetailsService.createUserDetails(userDetails);

        // Verify
        assertEquals(userDetails, result);
    }

    @Test
    void updateUserDetails() {
        // Prepare data
        UserDetails userDetails = new UserDetails();
        userDetails.setUserId(1);
        userDetails.setFirstName("John");

        UserDetails updatedUserDetails = new UserDetails();
        updatedUserDetails.setFirstName("Doe");

        // Mock repository method
        Mockito.when(userDetailsRepository.findById(1L)).thenReturn(Optional.of(userDetails));
        Mockito.when(userDetailsRepository.save(any(UserDetails.class))).thenReturn(updatedUserDetails);

        // Call service method
        UserDetails result = userDetailsService.updateUserDetails(1L, updatedUserDetails);

        // Verify
        assertEquals(updatedUserDetails.getFirstName(), result.getFirstName());
    }

    @Test
    void updateUserDetails_UserNotFound() {
        // Prepare data
        UserDetails updatedUserDetails = new UserDetails();

        // Mock repository method
        Mockito.when(userDetailsRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Call service method
        UserDetails result = userDetailsService.updateUserDetails(1L, updatedUserDetails);

        // Verify
        assertNull(result);
    }

    @Test
    void deleteUserDetails() {
        // Call service method
        userDetailsService.deleteUserDetails(1L);

        // Verify
        verify(userDetailsRepository, times(1)).deleteById(1L);
    }
}
