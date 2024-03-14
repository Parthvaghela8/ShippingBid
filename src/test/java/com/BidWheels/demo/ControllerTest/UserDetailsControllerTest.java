package com.BidWheels.demo.ControllerTest;

import com.BidWheels.demo.Controller.UserDetailsController;
import com.BidWheels.demo.Model.UserDetails;
import com.BidWheels.demo.service.UserDetailsService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class UserDetailsControllerTest {

    @Mock
    private UserDetailsService userDetailsService;

    @InjectMocks
    private UserDetailsController userDetailsController;

    private MockMvc mockMvc;

    @Test
    void getAllUserDetails() throws Exception {
        // Prepare data
        List<UserDetails> userDetailsList = new ArrayList<>();
        userDetailsList.add(new UserDetails());
        userDetailsList.add(new UserDetails());

        // Mock service method
        when(userDetailsService.getAllUserDetails()).thenReturn(userDetailsList);

        // Set up MockMvc
        mockMvc = MockMvcBuilders.standaloneSetup(userDetailsController).build();

        // Perform GET request
        mockMvc.perform(get("/api/user-details/getdata"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(userDetailsList.size()));
    }

    @Test
    void getUserDetailsById_UserDetailsFound() throws Exception {
        // Prepare data
        UserDetails userDetails = new UserDetails();
        userDetails.setUserId(1);

        // Mock service method
        when(userDetailsService.getUserDetailsById(1L)).thenReturn(userDetails);

        // Set up MockMvc
        mockMvc = MockMvcBuilders.standaloneSetup(userDetailsController).build();

        // Perform GET request
        mockMvc.perform(get("/api/user-details/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.userId").value(1));
    }
    @Test
    void getUserDetailsById_UserDetailsNotFound() throws Exception {
        // Mock service method
        when(userDetailsService.getUserDetailsById(anyLong())).thenReturn(null);

        // Set up MockMvc
        mockMvc = MockMvcBuilders.standaloneSetup(userDetailsController).build();

        // Perform GET request
        mockMvc.perform(get("/api/user-details/45"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.TEXT_PLAIN_VALUE + ";charset=ISO-8859-1")) // Adjust charset as needed
                .andExpect(content().string("UserDetails not found with ID: 45"));
    }


    // Similarly, you can write test cases for other methods like createUserDetails, updateUserDetails, and deleteUserDetails
    // Ensure to mock the service methods appropriately and perform assertions on the response status and content
}
