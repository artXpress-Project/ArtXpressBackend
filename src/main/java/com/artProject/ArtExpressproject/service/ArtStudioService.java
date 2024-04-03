package com.artProject.ArtExpressproject.service;

import com.artProject.ArtExpressproject.dto.ArtStudioDto;
import com.artProject.ArtExpressproject.dto.requestDto.CreateArtStudioRequest;
import com.artProject.ArtExpressproject.model.ArtStudio;
import com.artProject.ArtExpressproject.model.User;

import java.util.List;

public interface ArtStudioService {
    ArtStudio createArtStudio(CreateArtStudioRequest request, User user);

    ArtStudio updateArtStudio(Long artStudioId, CreateArtStudioRequest updateRequest);

    void deleteArtStudio(Long artStudioId);

    List<ArtStudio> getAllArtStudio();

    public List<ArtStudio> searchArtStudio(String keyword);

    ArtStudio findArtStudioById(Long id);

    ArtStudio getArtStudioByUserId(Long userId);

    ArtStudioDto addFavorites(Long artStudioId, User user);

    ArtStudio updateArtStudioStatus(Long id);

}
