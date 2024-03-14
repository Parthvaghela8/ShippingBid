package com.BidWheels.demo.Repositry;



import com.BidWheels.demo.Model.Bids;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface BidsRepository extends JpaRepository<Bids, Long> {
    // You can add custom query methods if needed

    // Custom query to insert a new bid
//    @Modifying
//    @Query(value = "INSERT INTO bids (shipment_id, shipper_id, bid_amount, bid_status, bid_time) " +
//            "VALUES (:shipmentId, :shipperId, :bidAmount, :bidStatus, :bidTime)",
//            nativeQuery = true)
//    void insertBid(Long shipmentId, Long shipperId, BigDecimal bidAmount, String bidStatus, LocalDateTime bidTime);
//
//    // You can add more custom queries as needed
}

