package com.BidWheels.demo.Model;



import jakarta.persistence.*;
import lombok.*;


import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Table(name = "bids")
public class Bids {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bid_id")
    private Long bidId;

    @Column(name = "shipment_id")
    private Long shipmentId;

    @Column(name = "shipper_id")
    private Long shipperId;

    @Column(name = "bid_amount")
    private BigDecimal bidAmount;

    @Column(name = "bid_status")
    private String bidStatus;

    @Column(name = "bid_time")
    private LocalDateTime bidTime;


    // Constructors, getters, setters...
}
