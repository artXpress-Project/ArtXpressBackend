package com.artProject.ArtExpressproject.service;

import com.artProject.ArtExpressproject.model.ArtStudio;
import com.artProject.ArtExpressproject.model.Genre;
import com.artProject.ArtExpressproject.repository.GenreRepository;
import com.artProject.ArtExpressproject.service.validationService.GenreVerification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService{

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private ArtStudioService artStudioService;

    @Autowired
    private GenreVerification genreVerification;
    @Override
    public Genre createGenre(String name, Long userId) {
        ArtStudio artStudio = artStudioService.getArtStudioByUserId(userId);
        Genre genre = new Genre();
        genre.setGenreName(name);
        genre.setArtStudio(artStudio);
        return genreRepository.save(genre);
    }

    @Override
    public List<Genre> findGenreByArtStudioId(Long id) {
        return genreVerification.findByArtStudioId(id);
    }

    @Override
    public Genre findGenreById(Long id) {
        return genreVerification.findGenreById(id);
    }
}
