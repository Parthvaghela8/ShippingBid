package com.BidWheels.demo.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @Column(name = "first_name", nullable = true)
    private String firstName;

    @Column(name = "last_name", nullable = true)
    private String lastName;

    @Column(name = "email", nullable = true)
    private String email;

//    @Column(name = "username", nullable = false)
//    private String username;

    @Column(name = "gender", nullable = true)
    private String gender;

//    @Column(name = "contact_number", nullable = true)
    private Integer contactNumber;
}
