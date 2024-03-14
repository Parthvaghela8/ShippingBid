package com.BidWheels.demo.Controller;

import com.BidWheels.demo.Model.FinalShipperForShipment;
import com.BidWheels.demo.service.FinalShipperForShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
