package com.artProject.ArtExpressproject.service;

import com.artProject.ArtExpressproject.dto.responseDto.ReviewResponse;
import com.artProject.ArtExpressproject.model.Artwork;
import com.artProject.ArtExpressproject.model.Review;
import com.artProject.ArtExpressproject.model.User;
import com.artProject.ArtExpressproject.repository.ReviewRepository;
import com.artProject.ArtExpressproject.service.validationService.ReviewVerification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService{

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ArtWorkService artWorkService;

    @Autowired
    private ReviewVerification reviewVerification;
    @Override
    public Review createReview(ReviewResponse reviewResponse, User user, Long artworkId) {
        Artwork artwork = artWorkService.findArtworkById(artworkId);
        Review review = new Review();
        review.setReviewBody(reviewResponse.getReviewBody());
        review.setUser(user);
        review.setArtwork(artwork);
        Review review1 = reviewRepository.save(review);
        artwork.getReviews().add(review1);
        return review1;
    }

    @Override
    public List<Review> getArtworkReviews(Long artworkId) {
        return reviewVerification.findByArtworkId(artworkId);
    }

    @Override
    public void deleteReview(Long id) {
        Review review = reviewVerification.findByUserId(id);
        reviewRepository.delete(review);

    }


}
