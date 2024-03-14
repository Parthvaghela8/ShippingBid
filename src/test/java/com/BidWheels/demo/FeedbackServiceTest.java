package com.BidWheels.demo;

import com.BidWheels.demo.Model.Feedback;
import com.BidWheels.demo.Repositry.FeedbackRepository;
import com.BidWheels.demo.service.FeedbackService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
class FeedbackServiceTest {

    @Mock
    private FeedbackRepository feedbackRepository;

    @InjectMocks
    private FeedbackService feedbackService;

    @Test
    void getAllFeedback() {
        // Prepare data
        List<Feedback> feedbackList = new ArrayList<>();
        feedbackList.add(new Feedback());
        feedbackList.add(new Feedback());

        // Mock repository method
        Mockito.when(feedbackRepository.findAll()).thenReturn(feedbackList);

        // Call service method
        List<Feedback> result = feedbackService.getAllFeedback();

        // Verify
        assertEquals(feedbackList.size(), result.size());
    }

    @Test
    void getFeedbackById() {
        // Prepare data
        Feedback feedback = new Feedback();
        feedback.setFeedbackId(1L);

        // Mock repository method
        Mockito.when(feedbackRepository.findById(1L)).thenReturn(Optional.of(feedback));

        // Call service method
        Feedback result = feedbackService.getFeedbackById(1L);

        // Verify
        assertEquals(feedback, result);
    }

    @Test
    void createFeedback() {
        // Prepare data
        Feedback feedback = new Feedback();

        // Mock repository method
        Mockito.when(feedbackRepository.save(any(Feedback.class))).thenReturn(feedback);

        // Call service method
        Feedback result = feedbackService.createFeedback(feedback);

        // Verify
        assertEquals(feedback, result);
    }

    @Test
    void updateFeedback() {
        // Prepare data
        Feedback feedback = new Feedback();
        feedback.setFeedbackId(1L);
        feedback.setRating(4);

        Feedback updatedFeedback = new Feedback();
        updatedFeedback.setRating(5);

        // Mock repository method
        Mockito.when(feedbackRepository.findById(1L)).thenReturn(Optional.of(feedback));
        Mockito.when(feedbackRepository.save(any(Feedback.class))).thenReturn(updatedFeedback);

        // Call service method
        Feedback result = feedbackService.updateFeedback(1L, updatedFeedback);

        // Verify
        assertEquals(updatedFeedback.getRating(), result.getRating());
    }

    @Test
    void updateFeedback_FeedbackNotFound() {
        // Prepare data
        Feedback updatedFeedback = new Feedback();

        // Mock repository method
        Mockito.when(feedbackRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Call service method
        Feedback result = feedbackService.updateFeedback(1L, updatedFeedback);

        // Verify
        assertNull(result);
    }

    @Test
    void deleteFeedback() {
        // Call service method
        feedbackService.deleteFeedback(1L);

        // Verify
        verify(feedbackRepository, times(1)).deleteById(1L);
    }
}
