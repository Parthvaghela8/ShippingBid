package com.BidWheels.demo.ControllerTest;

import com.BidWheels.demo.Controller.ShipmentController;
import com.BidWheels.demo.Model.Shipment;
import com.BidWheels.demo.Repositry.ShipmentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class ShipmentControllerTest {

    @Mock
    private ShipmentRepository shipmentRepository;

    @InjectMocks
    private ShipmentController shipmentController;

    private MockMvc mockMvc;

    @Test
    void getAllShipments() throws Exception {
        // Prepare data
        List<Shipment> shipments = new ArrayList<>();
        shipments.add(new Shipment());
        shipments.add(new Shipment());

        // Mock repository method
        when(shipmentRepository.findAll()).thenReturn(shipments);

        // Set up MockMvc
        mockMvc = MockMvcBuilders.standaloneSetup(shipmentController).build();

        // Perform GET request
        mockMvc.perform(get("/api/shipments/getdata"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(shipments.size()));
    }

    @Test
    void getShipmentById_ShipmentFound() throws Exception {
        // Prepare data
        Shipment shipment = new Shipment();
        shipment.setShipmentId(1L);

        // Mock repository method
        when(shipmentRepository.findById(1L)).thenReturn(Optional.of(shipment));

        // Set up MockMvc
        mockMvc = MockMvcBuilders.standaloneSetup(shipmentController).build();

        // Perform GET request
        mockMvc.perform(get("/api/shipments/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.shipmentId").value(1));
    }

    @Test
    void getShipmentById_ShipmentNotFound() throws Exception {
        // Mock repository method
        when(shipmentRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Set up MockMvc
        mockMvc = MockMvcBuilders.standaloneSetup(shipmentController).build();

        // Perform GET request
        mockMvc.perform(get("/api/shipments/45"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.TEXT_PLAIN_VALUE + ";charset=ISO-8859-1")) // Adjust charset as needed
                .andExpect(content().string("Shipment not found with ID: 45"));
    }

    // Similarly, you can write test cases for other methods like createShipment, updateShipment, and deleteShipment
    // Ensure to mock the repository methods appropriately and perform assertions on the response status and content
}
