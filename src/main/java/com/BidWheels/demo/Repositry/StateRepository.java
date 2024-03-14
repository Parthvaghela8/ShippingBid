package com.BidWheels.demo.Repositry;

import com.BidWheels.demo.Model.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<State, Long> {
}