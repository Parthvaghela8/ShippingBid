package com.BidWheels.demo.service;



import com.BidWheels.demo.Model.Feedback;
import com.BidWheels.demo.Repositry.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }

    public Feedback getFeedbackById(Long id) {
        return feedbackRepository.findById(id).orElse(null);
    }

    public Feedback createFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    public Feedback updateFeedback(Long id, Feedback updatedFeedback) {
        Feedback existingFeedback = feedbackRepository.findById(id).orElse(null);

        if (existingFeedback != null) {
            existingFeedback.setShipmentId(updatedFeedback.getShipmentId());
            existingFeedback.setCustomerId(updatedFeedback.getCustomerId());
            existingFeedback.setShipperId(updatedFeedback.getShipperId());
            existingFeedback.setRating(updatedFeedback.getRating());
            existingFeedback.setComment(updatedFeedback.getComment());

            return feedbackRepository.save(existingFeedback);
        } else {
            return null; // Handle feedback not found
        }
    }

    public void deleteFeedback(Long id) {
        feedbackRepository.deleteById(id);
    }
}

