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
    @Column(name = "final_deal_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long finalDealId;

    @OneToOne
    @JoinColumn(name = "bid_id", referencedColumnName = "bid_id")
    private Bids bid;

    // Constructors, getters, setters...
}
