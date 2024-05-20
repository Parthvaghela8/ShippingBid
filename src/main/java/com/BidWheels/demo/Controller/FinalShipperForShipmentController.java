package com.BidWheels.demo.Controller;

import com.BidWheels.demo.Model.FinalShipperForShipment;
import com.BidWheels.demo.service.FinalShipperForShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/finalshippers")
public class FinalShipperForShipmentController {

    @Autowired
    private FinalShipperForShipmentService finalShipperService;

    @GetMapping("/{finalDealId}")
    public ResponseEntity<FinalShipperForShipment> getFinalShipperDetails(@PathVariable Long finalDealId) {
        FinalShipperForShipment finalShipper = finalShipperService.getFinalShipperDetails(finalDealId);

        if (finalShipper != null) {
            return ResponseEntity.ok(finalShipper);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/shipments/all")
    public ResponseEntity<List<FinalShipperForShipment>> getAllShipments() {
        List<FinalShipperForShipment> shipments = finalShipperService.getAllShipments();
        if (!shipments.isEmpty()) {
            return ResponseEntity.ok(shipments);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // Return 204 No Content
        }
    }
}
