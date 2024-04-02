package com.artProject.ArtExpressproject.controller;


import com.artProject.ArtExpressproject.model.Genre;
import com.artProject.ArtExpressproject.model.User;
import com.artProject.ArtExpressproject.service.GenreService;
import com.artProject.ArtExpressproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @Autowired
    private UserService userService;

    @PostMapping("/admin/category")
    public ResponseEntity<Genre> createGenre(@RequestBody Genre genre,
                                             @RequestHeader("Authorization") String jwt) {
        User user = userService.findUserByJwt(jwt);
        Genre createGenre = genreService.createGenre(genre.getGenreName(), user.getId());
        return new ResponseEntity<>(createGenre, HttpStatus.CREATED);

    }


    @GetMapping("/genre/artStudio")
    public ResponseEntity<List<Genre>> getArtStudioGenre(
            @RequestHeader("Authorization") String jwt) {
        User user = userService.findUserByJwt(jwt);
        List<Genre> genre = genreService.findGenreByArtStudioId(user.getId());
        return new ResponseEntity<>(genre, HttpStatus.CREATED);


    }
}
