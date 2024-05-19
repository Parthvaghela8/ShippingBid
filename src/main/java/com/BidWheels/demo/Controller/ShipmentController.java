package com.BidWheels.demo.Controller;

import com.BidWheels.demo.Model.Address;
import com.BidWheels.demo.Model.Customer;
import com.BidWheels.demo.Model.ShipmentCategory;
import com.BidWheels.demo.Model.Shipment;
import com.BidWheels.demo.Repositry.AddressRepository;
import com.BidWheels.demo.Repositry.CustomerRepository;
import com.BidWheels.demo.Repositry.ShipmentCategoryRepository;
import com.BidWheels.demo.Repositry.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.*;

@RestController
@RequestMapping("/api/shipments")
public class ShipmentController {


    @Autowired
    private ShipmentRepository shipmentRepository;

    @Autowired
    private AddressRepository addressRepository; // Assuming you have AddressRepository

    @Autowired
    private ShipmentCategoryRepository shipmentcategoryRepository; // Assuming you have CategoryRepository

    @Autowired
    private CustomerRepository customerRepository; // Assuming you have CustomerRepository

    @GetMapping("/getdata")
    public ResponseEntity<Object> getAllShipments() {
        try {
            // Fetch shipments with related entities in a single query
            List<Object[]> shipmentData = shipmentRepository.findAllWithRelatedEntities();

            // Create a list to hold shipment data along with related entities
            List<Map<String, Object>> responseData = new ArrayList<>();

            for (Object[] row : shipmentData) {
                // Extract data from the Object[] array
                Shipment shipment = (Shipment) row[0];
                Address originAddress = (Address) row[1];
                Address destinationAddress = (Address) row[2];
                ShipmentCategory category = (ShipmentCategory) row[3];
                Customer customer = (Customer) row[4];

                // Create a map to hold the shipment and its related entities
                Map<String, Object> shipmentMap = new HashMap<>();
                shipmentMap.put("shipment", shipment);
                shipmentMap.put("originAddress", originAddress);
                shipmentMap.put("destinationAddress", destinationAddress);
                shipmentMap.put("category", category);
                shipmentMap.put("customer", customer);

                responseData.add(shipmentMap);
            }

            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving shipment data");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getShipmentById(@PathVariable Long id) {
        try {
            Optional<Shipment> shipmentOptional = shipmentRepository.findById(id);
            if (shipmentOptional.isPresent()) {
                Shipment shipment = shipmentOptional.get();
                // Fetch related entities
                Optional<Address> originAddressOptional = addressRepository.findById(Math.toIntExact(shipment.getOriginAddressId()));
                Optional<Address> destinationAddressOptional = addressRepository.findById(Math.toIntExact(shipment.getDestinationAddressId()));
                Optional<ShipmentCategory> categoryOptional = shipmentcategoryRepository.findById(shipment.getCategoryId());
                Optional<Customer> customerOptional = customerRepository.findById(shipment.getCustomerId());

                if (originAddressOptional.isPresent() && destinationAddressOptional.isPresent()
                        && categoryOptional.isPresent() && customerOptional.isPresent()) {
                    // Construct a DTO or Map to return
                    Map<String, Object> response = new HashMap<>();
                    response.put("shipment", shipment);
                    response.put("originAddress", originAddressOptional.get());
                    response.put("destinationAddress", destinationAddressOptional.get());
                    response.put("category", categoryOptional.get());
                    response.put("customer", customerOptional.get());

                    return ResponseEntity.ok(response);
                } else {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving related entities");
                }
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

    @PutMapping("/status/{id}")
    public Shipment updateShipmentStatus(@PathVariable("id") Long shipmentId,
                                         @RequestBody Map<String, String> requestBody) {
        String newStatus = requestBody.get("status");

        Shipment shipment = shipmentRepository.findById(shipmentId)
                .orElseThrow(() -> new RuntimeException("Shipment not found with id: " + shipmentId));

        shipment.setShipmentStatus(newStatus);

        return shipmentRepository.save(shipment);
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