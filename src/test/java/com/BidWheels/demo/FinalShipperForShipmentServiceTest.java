package com.BidWheels.demo;

import com.BidWheels.demo.Model.FinalShipperForShipment;
import com.BidWheels.demo.Repositry.FinalShipperForShipmentRepository;
import com.BidWheels.demo.service.FinalShipperForShipmentService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyLong;

@SpringBootTest
class FinalShipperForShipmentServiceTest {

    @Mock
    private FinalShipperForShipmentRepository finalShipperRepository;

    @InjectMocks
    private FinalShipperForShipmentService finalShipperService;

    @Test
    void getFinalShipperDetails_FinalShipperFound() {
        // Prepare data
        FinalShipperForShipment finalShipper = new FinalShipperForShipment();
        finalShipper.setFinalDealId(1L);

        // Mock repository method
        Mockito.when(finalShipperRepository.findById(1L)).thenReturn(Optional.of(finalShipper));

        // Call service method
        FinalShipperForShipment result = finalShipperService.getFinalShipperDetails(1L);

        // Verify
        assertEquals(finalShipper, result);
    }

    @Test
    void getFinalShipperDetails_FinalShipperNotFound() {
        // Mock repository method
        Mockito.when(finalShipperRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Call service method
        FinalShipperForShipment result = finalShipperService.getFinalShipperDetails(1L);

        // Verify
        assertNull(result);
    }
}
