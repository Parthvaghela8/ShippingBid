package com.BidWheels.demo;

import com.BidWheels.demo.Model.Shipper;
import com.BidWheels.demo.Repositry.ShipperRepository;
import com.BidWheels.demo.service.ShipperService;
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
class ShipperServiceTest {

    @Mock
    private ShipperRepository shipperRepository;

    @InjectMocks
    private ShipperService shipperService;

    @Test
    void getAllShippers() {
        // Prepare data
        List<Shipper> shippers = new ArrayList<>();
        shippers.add(new Shipper());
        shippers.add(new Shipper());

        // Mock repository method
        Mockito.when(shipperRepository.findAll()).thenReturn(shippers);

        // Call service method
        List<Shipper> result = shipperService.getAllShippers();

        // Verify
        assertEquals(shippers.size(), result.size());
    }

    @Test
    void getShipperById_ShipperFound() {
        // Prepare data
        Shipper shipper = new Shipper();
        shipper.setShipperId(1L);

        // Mock repository method
        Mockito.when(shipperRepository.findById(1L)).thenReturn(Optional.of(shipper));

        // Call service method
        Shipper result = shipperService.getShipperById(1L);

        // Verify
        assertEquals(shipper, result);
    }

    @Test
    void getShipperById_ShipperNotFound() {
        // Mock repository method
        Mockito.when(shipperRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Call service method
        Shipper result = shipperService.getShipperById(1L);

        // Verify
        assertNull(result);
    }

    @Test
    void getShipperDetailsByUserId_ShipperFound() {
        // Prepare data
        Shipper shipper = new Shipper();
        shipper.setUserId(1L);

        // Mock repository method
        Mockito.when(shipperRepository.findByUserId(1L)).thenReturn(Optional.of(shipper));

        // Call service method
        Shipper result = shipperService.getShipperDetailsByUserId(1L);

        // Verify
        assertEquals(shipper, result);
    }

    @Test
    void getShipperDetailsByUserId_ShipperNotFound() {
        // Mock repository method
        Mockito.when(shipperRepository.findByUserId(anyLong())).thenReturn(Optional.empty());

        // Call service method
        Shipper result = shipperService.getShipperDetailsByUserId(1L);

        // Verify
        assertNull(result);
    }
}
