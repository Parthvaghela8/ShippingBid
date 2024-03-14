package com.BidWheels.demo.Model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "cities")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private Long cityId;

    @Column(name = "city_name", nullable = false)
    private String cityName;

    @ManyToOne
    @JoinColumn(name = "state_id", nullable = false)
    private State state;

    // Constructors, getters, setters...
}
