package com.BidWheels.demo.Repositry;

import com.BidWheels.demo.Model.FinalShipperForShipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FinalShipperForShipmentRepository extends JpaRepository<FinalShipperForShipment, Long> {
    @Autowired
    public FinalShipperForShipmentRepository finalShipperRepository = null;

    public default List<FinalShipperForShipment> getAllShipments() {
        return finalShipperRepository.findAll();
    }
    FinalShipperForShipment findByShipperId(Long shipperId);

}
