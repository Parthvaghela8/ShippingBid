package com.BidWheels.demo.Repositry;

import com.BidWheels.demo.Model.State;
import org.springframework.data.jpa.repository.JpaRepository;

import com.BidWheels.demo.Model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
    // You can add custom query methods if needed
}