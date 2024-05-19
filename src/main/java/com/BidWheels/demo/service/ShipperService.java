package com.BidWheels.demo.service;

import com.BidWheels.demo.Model.Customer;
import com.BidWheels.demo.Model.Shipper;
import com.BidWheels.demo.Repositry.CustomerRepository;
import com.BidWheels.demo.Repositry.ShipperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipperService {

    @Autowired
    private ShipperRepository shipperRepository;

    @Autowired
    public ShipperService(ShipperRepository shipperRepository)
    {
        this.shipperRepository = shipperRepository;
    }

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


    public Shipper addShipper(Shipper shipper) {
        return shipperRepository.save(shipper);
    }

    public boolean deleteShipper(Long id) {
        if (shipperRepository.existsById(id)) {
            shipperRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
