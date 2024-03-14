package com.BidWheels.demo.service;

import com.BidWheels.demo.Model.FinalShipperForShipment;
import com.BidWheels.demo.Repositry.FinalShipperForShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FinalShipperForShipmentService {

    @Autowired
    private FinalShipperForShipmentRepository finalShipperRepository;

    public FinalShipperForShipment getFinalShipperDetails(Long finalDealId) {
        return finalShipperRepository.findById(finalDealId).orElse(null);
    }
}
