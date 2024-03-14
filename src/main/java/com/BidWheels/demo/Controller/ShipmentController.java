package com.BidWheels.demo.Controller;

import com.BidWheels.demo.Model.Shipment;
import com.BidWheels.demo.Repositry.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shipments")
public class ShipmentController {

    @Autowired
    private ShipmentRepository shipmentRepository;

    @GetMapping("/getdata")
    public ResponseEntity<List<Shipment>> getAllShipments() {
        try {
            List<Shipment> shipments = shipmentRepository.findAll();
            return ResponseEntity.ok(shipments);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getShipmentById(@PathVariable Long id) {
        try {
            Shipment shipment = shipmentRepository.findById(id).orElse(null);
            if (shipment != null) {
                return ResponseEntity.ok(shipment);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Shipment not found with ID: " + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving shipment");
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Object> createShipment(@RequestBody Shipment shipment) {
        try {
            Shipment createdShipment = shipmentRepository.save(shipment);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdShipment);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating shipment");
        }
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Object> updateShipment(@PathVariable Long id, @RequestBody Shipment updatedShipment) {
        try {
            Shipment existingShipment = shipmentRepository.findById(id).orElse(null);
            if (existingShipment != null) {
                existingShipment.setShipmentDate(updatedShipment.getShipmentDate());
                existingShipment.setDeliveryDate(updatedShipment.getDeliveryDate());
                existingShipment.setMaxBidAmount(updatedShipment.getMaxBidAmount());
                existingShipment.setBidStartTime(updatedShipment.getBidStartTime());
                existingShipment.setBidEndTime(updatedShipment.getBidEndTime());
                existingShipment.setImageUrl(updatedShipment.getImageUrl());
                existingShipment.setOriginAddressId(updatedShipment.getOriginAddressId());
                existingShipment.setDestinationAddressId(updatedShipment.getDestinationAddressId());
                existingShipment.setCategoryId(updatedShipment.getCategoryId());
                existingShipment.setDescription(updatedShipment.getDescription());
                existingShipment.setShipmentStatus(updatedShipment.getShipmentStatus());
                existingShipment.setCustomerId(updatedShipment.getCustomerId());

                shipmentRepository.save(existingShipment);
                return ResponseEntity.ok(existingShipment);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Shipment not found with ID: " + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating shipment");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteShipment(@PathVariable Long id) {
        try {
            Shipment existingShipment = shipmentRepository.findById(id).orElse(null);
            if (existingShipment != null) {
                shipmentRepository.delete(existingShipment);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Shipment not found with ID: " + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting shipment");
        }
    }
}
