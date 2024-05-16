package com.artProject.ArtExpressproject.service.validationService;

import com.artProject.ArtExpressproject.customException.NotFoundException;
import com.artProject.ArtExpressproject.model.ArtStudio;
import com.artProject.ArtExpressproject.model.Genre;
import com.artProject.ArtExpressproject.repository.GenreRepository;
import com.artProject.ArtExpressproject.service.ArtStudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Validated
public class GenreVerification {
    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private ArtStudioService artStudioService;

    public List<Genre> findByArtStudioId(Long id) {
//        ArtStudio artStudio = artStudioService.getArtStudioByUserId(id);
        List<Genre> genres = genreRepository.findALLGenreByArtStudioId(id);
        if(genres.isEmpty()) return new ArrayList<>();
        else return genres;
     }

     public Genre findGenreById(Long id){
        Optional<Genre> optionalGenre = genreRepository.findById(id);
        if(optionalGenre.isEmpty()) throw new NotFoundException("Genre Not found");
        else return optionalGenre.get();
     }
}
