package com.BidWheels.demo.ControllerTest;

import com.BidWheels.demo.Controller.BidController;
import com.BidWheels.demo.Model.Bids;
import com.BidWheels.demo.service.BidService;
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
class BidControllerTest {

    @Mock
    private BidService bidService;

    @InjectMocks
    private BidController bidController;

    private MockMvc mockMvc;

    @Test
    void getAllBids() throws Exception {
        // Prepare data
        List<Bids> bids = new ArrayList<>();
        bids.add(new Bids());
        bids.add(new Bids());

        // Mock service method
        when(bidService.getAllBids()).thenReturn(bids);

        // Set up MockMvc
        mockMvc = MockMvcBuilders.standaloneSetup(bidController).build();

        // Perform GET request
        mockMvc.perform(get("/api/bids/getdata"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(bids.size()));
    }

    @Test
    void getBidById_BidFound() throws Exception {
        // Prepare data
        Bids bid = new Bids();
        bid.setBidId(1L);

        // Mock service method
        when(bidService.getBidById(1L)).thenReturn(bid);

        // Set up MockMvc
        mockMvc = MockMvcBuilders.standaloneSetup(bidController).build();

        // Perform GET request
        mockMvc.perform(get("/api/bids/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.bidId").value(1));
    }

    @Test
    void getBidById_BidNotFound() throws Exception {
        // Mock service method
        when(bidService.getBidById(anyLong())).thenReturn(null);

        // Set up MockMvc
        mockMvc = MockMvcBuilders.standaloneSetup(bidController).build();

        // Perform GET request
        mockMvc.perform(get("/api/bids/45"))
                .andExpect(status().isNotFound());
    }

    @Test
    void createBid() throws Exception {
        // Prepare data
        Bids bid = new Bids();
        bid.setBidId(1L);

        // Mock service method
        when(bidService.createBid(any(Bids.class))).thenReturn(bid);

        // Set up MockMvc
        mockMvc = MockMvcBuilders.standaloneSetup(bidController).build();

        // Perform POST request
        mockMvc.perform(post("/api/bids/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.bidId").value(1)); // Ensure the returned JSON contains the 'id' field
    }
    @Test
    void updateBid_BidFound() throws Exception {
        // Prepare data
        Bids bid = new Bids();
        bid.setBidId(1L);

        // Mock service method
        when(bidService.updateBid(anyLong(), any(Bids.class))).thenReturn(bid);

        // Set up MockMvc
        mockMvc = MockMvcBuilders.standaloneSetup(bidController).build();

        // Perform PUT request
        mockMvc.perform(put("/api/bids/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.bidId").value(1));
    }

    @Test
    void updateBid_BidNotFound() throws Exception {
        // Mock service method
        when(bidService.updateBid(anyLong(), any(Bids.class))).thenReturn(null);

        // Set up MockMvc
        mockMvc = MockMvcBuilders.standaloneSetup(bidController).build();

        // Perform PUT request
        mockMvc.perform(put("/api/bids/update/45")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":45}"))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteBid() throws Exception {
        // Set up MockMvc
        mockMvc = MockMvcBuilders.standaloneSetup(bidController).build();

        // Perform DELETE request
        mockMvc.perform(delete("/api/bids/delete/1"))
                .andExpect(status().isOk());
    }
}
