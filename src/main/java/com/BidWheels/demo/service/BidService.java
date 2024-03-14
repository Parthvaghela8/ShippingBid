package com.BidWheels.demo.service;

import com.BidWheels.demo.Model.Bids;
import com.BidWheels.demo.Repositry.BidsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BidService {

    @Autowired
    private BidsRepository bidRepository;

    public List<Bids> getAllBids() {
        try {
            return bidRepository.findAll();
        } catch (Exception e) {
            // Handle exceptions (e.g., log, throw custom exception)
            throw new RuntimeException("Error getting all bids", e);
        }
    }

    public Bids getBidById(Long id) {
        try {
            Optional<Bids> bidOptional = bidRepository.findById(id);
            return bidOptional.orElse(null);
        } catch (Exception e) {
            // Handle exceptions (e.g., log, throw custom exception)
            throw new RuntimeException("Error getting bid by ID: " + id, e);
        }
    }

    public Bids createBid(Bids bid) {
        try {
            return bidRepository.save(bid);
        } catch (Exception e) {
            // Handle exceptions (e.g., log, throw custom exception)
            throw new RuntimeException("Error creating bid", e);
        }
    }

    public Bids updateBid(Long id, Bids updatedBid) {
        try {
            Bids existingBid = bidRepository.findById(id).orElse(null);

            if (existingBid != null) {
                // Update existingBid with values from updatedBid
                existingBid.setShipmentId(updatedBid.getShipmentId());
                existingBid.setShipperId(updatedBid.getShipperId());
                existingBid.setBidAmount(updatedBid.getBidAmount());
                existingBid.setBidStatus(updatedBid.getBidStatus());
                existingBid.setBidTime(updatedBid.getBidTime());

                return bidRepository.save(existingBid);
            } else {
                // Handle bid not found
                return null;
            }
        } catch (Exception e) {
            // Handle exceptions (e.g., log, throw custom exception)
            throw new RuntimeException("Error updating bid with ID: " + id, e);
        }
    }

    public void deleteBid(Long id) {
        try {
            bidRepository.deleteById(id);
        } catch (Exception e) {
            // Handle exceptions (e.g., log, throw custom exception)
            throw new RuntimeException("Error deleting bid with ID: " + id, e);
        }
    }
}
