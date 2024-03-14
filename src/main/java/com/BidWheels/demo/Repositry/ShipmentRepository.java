package com.BidWheels.demo.Repositry;

import com.BidWheels.demo.Model.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
    // You can add custom query methods if needed

    List<Shipment> findByShipmentStatus(String shipmentStatus);
}