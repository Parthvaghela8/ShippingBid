package com.BidWheels.demo.Repositry;

import com.BidWheels.demo.Model.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
    // You can add custom query methods if needed

    @Query("SELECT s, a1, a2, sc, c FROM Shipment s " +
            "JOIN Address a1 ON s.originAddressId = a1.id " +
            "JOIN Address a2 ON s.destinationAddressId = a2.id " +
            "JOIN ShipmentCategory sc ON s.categoryId = sc.id " +
            "JOIN Customer c ON s.customerId = c.id")
    List<Object[]> findAllWithRelatedEntities();

    @Query("SELECT s, oa, da, c, cu FROM Shipment s " +
            "LEFT JOIN Address oa ON s.originAddressId = oa.id " +
            "LEFT JOIN Address da ON s.destinationAddressId = da.id " +
            "LEFT JOIN ShipmentCategory c ON s.categoryId = c.id " +
            "LEFT JOIN Customer cu ON s.customerId = cu.id " +
            "WHERE s.id = :id")
    Optional<Object[]> findShipmentWithRelatedEntitiesById(@Param("id") Long id);



    List<Shipment> findByShipmentStatus(String shipmentStatus);
}
