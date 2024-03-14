package com.BidWheels.demo.Model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "user_details")
public class UserDetails {

    @Id
    @Column(name = "user_id")
    private int userId;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "date_of_birth", nullable = false)
    private java.sql.Date dateOfBirth;

    @Column(name = "contact_number", nullable = false)
    private int contactNumber;



    // Constructors, getters, setters...
}
