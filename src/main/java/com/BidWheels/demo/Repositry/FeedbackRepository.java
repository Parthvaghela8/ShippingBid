package com.BidWheels.demo.Repositry;



import com.BidWheels.demo.Model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    // You can add custom query methods if needed
}

