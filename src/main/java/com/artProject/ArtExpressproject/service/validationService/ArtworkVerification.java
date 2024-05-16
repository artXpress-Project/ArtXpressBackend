package com.artProject.ArtExpressproject.service.validationService;


import com.artProject.ArtExpressproject.customException.NotFoundException;
import com.artProject.ArtExpressproject.model.Artwork;
import com.artProject.ArtExpressproject.repository.ArtStudioRepository;
import com.artProject.ArtExpressproject.repository.ArtworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Validated
@Service
public class ArtworkVerification {

    @Autowired
    private ArtworkRepository artworkRepository;

    public List<Artwork> findByArtStudioId(Long id){
       List<Artwork> artwork = artworkRepository.findByArtStudioId(id);
       if(artwork.isEmpty()) return new ArrayList<>();
       else return artwork;

    }

    public List<Artwork> searchByQuery(String keyword){
        List<Artwork> artworks = artworkRepository.searchArtwork(keyword);
        if(artworks.isEmpty()) throw new NotFoundException("artwork not found with the keyword " + keyword);
        else return artworks;

    }

    public Artwork findByArtworkId(Long id){
        Optional<Artwork> optionalArtwork = artworkRepository.findById(id);
        if (optionalArtwork.isEmpty()){
            throw new NotFoundException("artwork does not exist....");
        }else return optionalArtwork.get();
    }

}
