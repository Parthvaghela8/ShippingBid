package com.BidWheels.demo;

import com.BidWheels.demo.Controller.ShipmentController;
import com.BidWheels.demo.Model.Shipment;
import com.BidWheels.demo.Repositry.ShipmentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
class ShipmentControllerTest {

    @Mock
    private ShipmentRepository shipmentRepository;

    @InjectMocks
    private ShipmentController shipmentController;

    @Test
    void getAllShipments() {
        // Prepare data
        List<Shipment> shipmentList = new ArrayList<>();
        shipmentList.add(new Shipment());
        shipmentList.add(new Shipment());

        // Mock repository method
        Mockito.when(shipmentRepository.findAll()).thenReturn(shipmentList);

        // Call controller method
        ResponseEntity<List<Shipment>> responseEntity = shipmentController.getAllShipments();

        // Verify
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(shipmentList.size(), responseEntity.getBody().size());
    }

    @Test
    void getShipmentById_ShipmentFound() {
        // Prepare data
        Shipment shipment = new Shipment();
        shipment.setShipmentId(1L);

        // Mock repository method
        Mockito.when(shipmentRepository.findById(1L)).thenReturn(Optional.of(shipment));

        // Call controller method
        ResponseEntity<Object> responseEntity = shipmentController.getShipmentById(1L);

        // Verify
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(shipment, responseEntity.getBody());
    }

    @Test
    void getShipmentById_ShipmentNotFound() {
        // Mock repository method
        Mockito.when(shipmentRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Call controller method
        ResponseEntity<Object> responseEntity = shipmentController.getShipmentById(1L);

        // Verify
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("Shipment not found with ID: 1", responseEntity.getBody());
    }

    @Test
    void createShipment() {
        // Prepare data
        Shipment shipment = new Shipment();

        // Mock repository method
        Mockito.when(shipmentRepository.save(any(Shipment.class))).thenReturn(shipment);

        // Call controller method
        ResponseEntity<Object> responseEntity = shipmentController.createShipment(shipment);

        // Verify
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(shipment, responseEntity.getBody());
    }

    // Add similar test cases for updateShipment and deleteShipment methods...
}
