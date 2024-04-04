package com.artProject.ArtExpressproject.service.validationService;


import com.artProject.ArtExpressproject.customException.NotFoundException;
import com.artProject.ArtExpressproject.model.Review;
import com.artProject.ArtExpressproject.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class ReviewVerification {

    @Autowired
    private ReviewRepository reviewRepository;

    public List<Review> findByArtworkId(Long artworkId){
        List<Review> review = reviewRepository.findByArtworkId(artworkId);
        if(review.isEmpty()){
            throw new NotFoundException("No reviews Found.....");
        }else return review;
    }

    public Review findByUserId(Long id){
        Review review = reviewRepository.findByUserId(id);
        if(review == null) throw new NotFoundException("Not found...");
        else return review;
    }


}
