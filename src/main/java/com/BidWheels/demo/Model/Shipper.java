package com.BidWheels.demo.Model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "shippers")
public class Shipper {

    @Id
    @Column(name = "shipper_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shipperId;

    @Column(name = "user_id", nullable = false, unique = true)
    private Long userId;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private UserDetails userDetails;

    // Constructors, getters, setters...
}
