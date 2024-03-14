package com.BidWheels.demo.Repositry;

import com.BidWheels.demo.Model.Shipper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShipperRepository extends JpaRepository<Shipper, Long> {
    Optional<Shipper> findByUserId(Long userId);
    // You can add custom query methods if needed
}
