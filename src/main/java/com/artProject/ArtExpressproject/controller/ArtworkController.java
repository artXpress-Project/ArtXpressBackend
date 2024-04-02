package com.artProject.ArtExpressproject.controller;


import com.artProject.ArtExpressproject.model.Artwork;
import com.artProject.ArtExpressproject.model.User;
import com.artProject.ArtExpressproject.service.ArtWorkService;
import com.artProject.ArtExpressproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/artwork")
public class ArtworkController {

    @Autowired
    private ArtWorkService artWorkService;

    @Autowired
    private UserService userService;


    @GetMapping("/search")
    public ResponseEntity<List<Artwork>> searchArtwork(@RequestParam String keyword,
                                                      @RequestHeader("Authorization") String jwt){
        User user = userService.findUserByJwt(jwt);
        List<Artwork> artwork = artWorkService.searchArtwork(keyword);
        return new ResponseEntity<>(artwork, HttpStatus.CREATED);

    }

    @GetMapping("/artStudio/{artStudioId}")
    public ResponseEntity<List<Artwork>> getArtStudioArtwork(@PathVariable Long artStudioId,
                                                             @RequestParam(required = false) String artworkCategory,
                                                       @RequestHeader("Authorization") String jwt){
        User user = userService.findUserByJwt(jwt);
        List<Artwork> artwork = artWorkService.getArtStudiosArtwork(artStudioId,artworkCategory);
        return new ResponseEntity<>(artwork, HttpStatus.OK);

    }

}
