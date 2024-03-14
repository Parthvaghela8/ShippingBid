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

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @ManyToOne
    @JoinColumn(name = "state_id", nullable = false)
    private State state;

    @Column(name = "postal_code", nullable = false)
    private Long postalCode;

    // Constructors, getters, setters...
}
