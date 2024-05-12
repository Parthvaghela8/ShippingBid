package com.BidWheels.demo.service;


import com.BidWheels.demo.Model.Shipment;
import com.BidWheels.demo.Repositry.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShipmentService {

    private final ShipmentRepository shipmentRepository;

    @Autowired
    public ShipmentService(ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }

    public Shipment getShipmentById(Long shipmentId) {
        Optional<Shipment> optionalShipment = shipmentRepository.findById(shipmentId);
        return optionalShipment.orElse(null);
    }

    // Add more methods for handling shipment-related operations if needed
}