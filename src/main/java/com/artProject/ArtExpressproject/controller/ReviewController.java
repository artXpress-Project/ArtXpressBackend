package com.artProject.ArtExpressproject.controller;

import com.artProject.ArtExpressproject.dto.responseDto.MessageResponse;
import com.artProject.ArtExpressproject.dto.responseDto.ReviewResponse;
import com.artProject.ArtExpressproject.model.Review;
import com.artProject.ArtExpressproject.model.User;
import com.artProject.ArtExpressproject.repository.UserRepository;
import com.artProject.ArtExpressproject.service.ReviewService;
import com.artProject.ArtExpressproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/review")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserService userService;

    @PostMapping("/create/{artworkId}")
    private ResponseEntity<Review> giveReview(
                                              @PathVariable Long artworkId,
                                              @RequestBody ReviewResponse response,
                                              @RequestHeader("Authorization")String jwt){
        User user = userService.findUserByJwt(jwt);
        Review review = reviewService.createReview(response,user,artworkId);
        return new ResponseEntity<>(review, HttpStatus.CREATED);

    }

    @GetMapping("/getAll/{artworkId}")
    private ResponseEntity<List<Review>> getArtworkReview(@PathVariable Long artworkId,
                                                          @RequestHeader("Authorization")String jwt){
        User user = userService.findUserByJwt(jwt);
        List<Review> list = reviewService.getArtworkReviews(artworkId);
        return new ResponseEntity<>(list, HttpStatus.CREATED);

    }

    @DeleteMapping("/delete")
    private ResponseEntity<MessageResponse> deleteUserReview(@RequestHeader("Authorization")String jwt){
        User user = userService.findUserByJwt(jwt);
        reviewService.deleteReview(user.getId());
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setMessage("Deleted Successfully");
        return new ResponseEntity<>(messageResponse, HttpStatus.CREATED);

    }




}
