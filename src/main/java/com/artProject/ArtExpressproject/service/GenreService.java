package com.artProject.ArtExpressproject.service;

import com.artProject.ArtExpressproject.model.Genre;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GenreService {
    Genre createGenre(String name, Long userId);
    List<Genre> findGenreByArtStudioId(Long id);

    Genre findGenreById(Long id);
}
