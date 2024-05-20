package com.BidWheels.demo.Model;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "final_shipper_for_shipment")
public class FinalShipperForShipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "final_deal_id")
    private Long finalDealId;

    @OneToOne
    @JoinColumn(name = "bid_id", referencedColumnName = "bid_id")
    private Bids bid;

    @Column(name = "shipment_id")
    private Long shipmentId;

    @Column(name = "shipper_id")
    private Long shipperId;

    // Constructors, getters, setters...
}
