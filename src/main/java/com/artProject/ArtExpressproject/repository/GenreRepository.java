package com.artProject.ArtExpressproject.repository;

import com.artProject.ArtExpressproject.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre,Long> {

    List<Genre> findALLGenreByArtStudioId(Long id);
}
