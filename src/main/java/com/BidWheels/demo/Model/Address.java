package com.BidWheels.demo.Model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long addressId;

    @Column(name = "street_address", nullable = false)
    private String streetAddress;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;
    
    @Column(name = "postal_code", nullable = false)
    private Long postalCode;

    // Constructors, getters, setters...
}
