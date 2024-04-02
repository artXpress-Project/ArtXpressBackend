package com.artProject.ArtExpressproject.repository;

import com.artProject.ArtExpressproject.model.Artwork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArtworkRepository extends JpaRepository<Artwork,Long> {


    List<Artwork> findByArtStudioId(Long artStudioId);

    @Query("SELECT a FROM Artwork a WHERE  a.name LIKE %:keyword% OR a.genre.genreName LIKE %:keyword OR a.artStudio.businessName LIKE %:keyword")
    List<Artwork> searchArtwork(@Param("keyword") String keyword);
}
