package com.BidWheels.demo.Model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "feedback")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_id")
    private Long feedbackId;

    @Column(name = "shipment_id")
    private Long shipmentId;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "shipper_id")
    private Long shipperId;

    @Column(name = "rating")
    private int rating;

    @Column(name = "comment", length = 255)
    private String comment;

    // Constructors, getters, setters...
}

