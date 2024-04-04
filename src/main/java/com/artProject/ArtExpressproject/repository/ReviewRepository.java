package com.artProject.ArtExpressproject.repository;

import com.artProject.ArtExpressproject.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {
    List<Review> findByArtworkId(Long id);

    Review findByUserId(Long id);

}
