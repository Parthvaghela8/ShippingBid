package com.BidWheels.demo.Repositry;



import com.BidWheels.demo.Model.Bids;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


public interface BidsRepository extends JpaRepository<Bids, Long> {
    List<Bids> findByShipmentId(Long shipmentId);
}