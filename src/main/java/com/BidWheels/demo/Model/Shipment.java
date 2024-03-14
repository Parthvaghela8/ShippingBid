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
@Table(name = "shipments")
public class   Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shipment_id")
    private Long shipmentId;

    @Column(name = "shipment_date", nullable = false)
    private LocalDateTime shipmentDate;

    @Column(name = "delivery_date", nullable = false)
    private LocalDateTime deliveryDate;

    @Column(name = "max_bid_amount", nullable = false)
    private BigDecimal maxBidAmount;

    @Column(name = "bid_start_time", nullable = false)
    private LocalDateTime bidStartTime;

    @Column(name = "bid_end_time", nullable = false)
    private LocalDateTime bidEndTime;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(name = "origin_address_id", nullable = false)
    private Long originAddressId;

    @Column(name = "destination_address_id", nullable = false)
    private Long destinationAddressId;

    @Column(name = "category_id", nullable = false)
    private Long categoryId;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "shipment_status", nullable = false)
    private String shipmentStatus;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;


    // Getters, setters, constructors, etc.

    // Constructors, getters, and setters...
}
