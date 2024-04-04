package com.artProject.ArtExpressproject.service;

import com.artProject.ArtExpressproject.dto.responseDto.ReviewResponse;
import com.artProject.ArtExpressproject.model.Artwork;
import com.artProject.ArtExpressproject.model.Review;
import com.artProject.ArtExpressproject.model.User;

import java.util.List;

public interface ReviewService {

    Review createReview(ReviewResponse reviewResponse, User user, Long artworkId);

    List<Review> getArtworkReviews(Long artworkId);

    void deleteReview(Long id);




}
