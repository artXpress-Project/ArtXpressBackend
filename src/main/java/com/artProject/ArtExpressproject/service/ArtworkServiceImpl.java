package com.artProject.ArtExpressproject.service;

import com.artProject.ArtExpressproject.dto.requestDto.CreateArtWorkRequest;
import com.artProject.ArtExpressproject.model.ArtStudio;
import com.artProject.ArtExpressproject.model.Artwork;
import com.artProject.ArtExpressproject.model.Genre;
import com.artProject.ArtExpressproject.repository.ArtStudioRepository;
import com.artProject.ArtExpressproject.repository.ArtworkRepository;
import com.artProject.ArtExpressproject.service.validationService.ArtworkVerification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArtworkServiceImpl implements ArtWorkService{
    @Autowired
    private ArtworkRepository artworkRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ArtworkVerification artworkVerification;
    @Override
    public Artwork createArtwork(CreateArtWorkRequest request, Genre genre, ArtStudio artStudio) {
        Artwork artwork = modelMapper.map(request,Artwork.class);
        artwork.setArtStudio(artStudio);
        artwork.setGenre(genre);
        artwork.setAvailable(request.isAvailable());
        Artwork savedArtwork = artworkRepository.save(artwork);
        artStudio.getArtworks().add(savedArtwork);
        return savedArtwork;
    }

    @Override
    public void deleteArtwork(Long artworkId) {
        Artwork artwork = findArtworkById(artworkId);
        artwork.setArtStudio(null);
        artworkRepository.save(artwork);

    }

    @Override
    public List<Artwork> getArtStudiosArtwork(Long artStudioId, String artworkCategory) {
        List<Artwork> artworks = artworkVerification.findByArtStudioId(artStudioId);
        if(artworkCategory!= null && !artworkCategory.equals("") ){
            artworks=filterByCategory(artworks,artworkCategory);
        }

        
        return artworks;
    }

    private List<Artwork> filterByCategory(List<Artwork> artworks, String artworkCategory) {
        return artworks.stream().filter(artwork -> {
            if(artwork.getGenre() != null){
                return artwork.getGenre().getGenreName().equals(artworkCategory);
            }
            return false;
        }).collect(Collectors.toList());
    }

    @Override
    public List<Artwork> searchArtwork(String keyword) {
        return artworkVerification.searchByQuery(keyword);
    }

    @Override
    public Artwork findArtworkById(Long artworkId) {
        return artworkVerification.findByArtworkId(artworkId);
    }

    @Override
    public Artwork updateAvailabilityStatus(Long artworkId) {
        Artwork artwork = findArtworkById(artworkId);
        artwork.setAvailable(!artwork.isAvailable());
        return artworkRepository.save(artwork);

    }
}
