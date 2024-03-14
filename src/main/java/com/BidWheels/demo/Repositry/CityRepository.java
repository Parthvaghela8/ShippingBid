package com.BidWheels.demo.Repositry;

import com.BidWheels.demo.Model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}