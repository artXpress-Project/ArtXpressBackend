package com.artProject.ArtExpressproject.service;

import com.artProject.ArtExpressproject.dto.requestDto.CreateArtWorkRequest;
import com.artProject.ArtExpressproject.model.ArtStudio;
import com.artProject.ArtExpressproject.model.Artwork;
import com.artProject.ArtExpressproject.model.Genre;

import java.util.List;

public interface ArtWorkService {
    Artwork createArtwork(CreateArtWorkRequest request, Genre genre, ArtStudio artStudio);

    void deleteArtwork(Long artworkId);

    List<Artwork> getArtStudiosArtwork(Long artStudioId, String artworkCategory);

    List<Artwork> searchArtwork(String keyword);

    Artwork findArtworkById(Long artworkId);

    Artwork updateAvailabilityStatus(Long artworkId);


}
