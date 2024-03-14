package com.BidWheels.demo.Controller;

import com.BidWheels.demo.Model.Bids;
import com.BidWheels.demo.service.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/api/bids")
public class BidController {

    @Autowired
    private BidService bidService;

    @GetMapping("/getdata")
    public ResponseEntity<List<Bids>> getAllBids() {
        try {
            List<Bids> bids = bidService.getAllBids();
            return ResponseEntity.ok(bids);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null); // Internal Server Error
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bids> getBidById(@PathVariable Long id) {
        try {
            Bids bid = bidService.getBidById(id);
            if (bid != null) {
                return ResponseEntity.ok(bid);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null); // Internal Server Error
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Bids> createBid(@RequestBody Bids bid) {
        try {
            Bids createdBid = bidService.createBid(bid);
            return ResponseEntity.ok(createdBid);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null); // Internal Server Error
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Bids> updateBid(@PathVariable Long id, @RequestBody Bids updatedBid) {
        try {
            Bids bid = bidService.updateBid(id, updatedBid);
            if (bid != null) {
                return ResponseEntity.ok(bid);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null); // Internal Server Error
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteBid(@PathVariable Long id) {
        try {
            bidService.deleteBid(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null); // Internal Server Error
        }
    }
}
