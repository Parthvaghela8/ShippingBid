package com.BidWheels.demo.service;

import com.BidWheels.demo.Model.Shipper;
import com.BidWheels.demo.Repositry.ShipperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipperService {

    @Autowired
    private ShipperRepository shipperRepository;

    public List<Shipper> getAllShippers() {
        return shipperRepository.findAll();
    }

    public Shipper getShipperById(Long shipperId) {
        return shipperRepository.findById(shipperId).orElse(null);
    }

    // Add other methods as needed...

    public Shipper getShipperDetailsByUserId(Long userId) {
        return (Shipper) shipperRepository.findByUserId(userId).orElse(null);
    }


}
