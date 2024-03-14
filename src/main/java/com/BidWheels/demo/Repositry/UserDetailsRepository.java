package com.BidWheels.demo.Repositry;

import com.BidWheels.demo.Model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
    // You can add custom query methods if needed
}
