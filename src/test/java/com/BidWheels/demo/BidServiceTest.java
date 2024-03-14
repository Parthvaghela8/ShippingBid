package com.BidWheels.demo;

import com.BidWheels.demo.Model.Bids;
import com.BidWheels.demo.Repositry.BidsRepository;
import com.BidWheels.demo.service.BidService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

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
class BidServiceTest {

    @Mock
    private BidsRepository bidRepository;

    @InjectMocks
    private BidService bidService;

    @Test
    void getAllBids() {
        // Prepare data
        List<Bids> bidsList = new ArrayList<>();
        bidsList.add(new Bids());
        bidsList.add(new Bids());

        // Mock repository method
        Mockito.when(bidRepository.findAll()).thenReturn(bidsList);

        // Call service method
        List<Bids> result = bidService.getAllBids();

        // Verify
        assertEquals(bidsList.size(), result.size());
    }

    @Test
    void getBidById() {
        // Prepare data
        Bids bid = new Bids();
        bid.setBidId(1L);

        // Mock repository method
        Mockito.when(bidRepository.findById(1L)).thenReturn(Optional.of(bid));

        // Call service method
        Bids result = bidService.getBidById(1L);

        // Verify
        assertEquals(bid, result);
    }

    @Test
    void createBid() {
        // Prepare data
        Bids bid = new Bids();

        // Mock repository method
        Mockito.when(bidRepository.save(any(Bids.class))).thenReturn(bid);

        // Call service method
        Bids result = bidService.createBid(bid);

        // Verify
        assertEquals(bid, result);
    }

    @Test
    void updateBid() {
        // Prepare data
        Bids bid = new Bids();
        bid.setBidId(1L);
        bid.setBidAmount(BigDecimal.valueOf(100));

        Bids updatedBid = new Bids();
        updatedBid.setBidAmount(BigDecimal.valueOf(150));

        // Mock repository method
        Mockito.when(bidRepository.findById(1L)).thenReturn(Optional.of(bid));
        Mockito.when(bidRepository.save(any(Bids.class))).thenReturn(updatedBid);

        // Call service method
        Bids result = bidService.updateBid(1L, updatedBid);

        // Verify
        assertEquals(updatedBid.getBidAmount(), result.getBidAmount());
    }

    @Test
    void updateBid_BidNotFound() {
        // Prepare data
        Bids updatedBid = new Bids();

        // Mock repository method
        Mockito.when(bidRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Call service method
        Bids result = bidService.updateBid(1L, updatedBid);

        // Verify
        assertNull(result);
    }

    @Test
    void deleteBid() {
        // Call service method
        bidService.deleteBid(1L);

        // Verify
        verify(bidRepository, times(1)).deleteById(1L);
    }
}
