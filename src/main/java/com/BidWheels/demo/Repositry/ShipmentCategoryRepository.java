package com.BidWheels.demo.Repositry;

import com.BidWheels.demo.Model.ShipmentCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentCategoryRepository extends JpaRepository<ShipmentCategory, Long> {
    // You can add custom query methods if needed
}
