package com.artProject.ArtExpressproject.controller;

import com.artProject.ArtExpressproject.dto.ArtStudioDto;
import com.artProject.ArtExpressproject.dto.requestDto.CreateArtStudioRequest;
import com.artProject.ArtExpressproject.model.ArtStudio;
import com.artProject.ArtExpressproject.model.User;
import com.artProject.ArtExpressproject.service.ArtStudioService;
import com.artProject.ArtExpressproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/artStudio")
public class ArtStudioCollectorController {

    @Autowired
    private ArtStudioService artStudioService;

    @Autowired
    private UserService userService;

    @GetMapping("/search")
    public ResponseEntity<List<ArtStudio>> searchArtStudio(
                                                     @RequestHeader("Authorization") String jwt,
                                                     @RequestParam String keyword
                                                     ){
        User user = userService.findUserByJwt(jwt);
        List<ArtStudio> artStudio = artStudioService.searchArtStudio(keyword);
        return new ResponseEntity<>(artStudio, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<ArtStudio>> getAllArtStudio(
            @RequestHeader("Authorization") String jwt


    ){
        User user = userService.findUserByJwt(jwt);
       List<ArtStudio> artStudio = artStudioService.getAllRestaurant();
        return new ResponseEntity<>(artStudio, HttpStatus.OK);
    }



    @GetMapping("/{id}")
    public ResponseEntity<ArtStudio> findArtStudioById(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id

    ){
        User user = userService.findUserByJwt(jwt);
        ArtStudio artStudio = artStudioService.findArtStudioById(id);
        return new ResponseEntity<>(artStudio, HttpStatus.OK);
    }

    @PutMapping ("/{id}/add-favorites")
    public ResponseEntity<ArtStudioDto> addToFavorite(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id

    ){
        User user = userService.findUserByJwt(jwt);
        ArtStudioDto artStudioDto = artStudioService.addFavorites(id,user);
        return new ResponseEntity<>(artStudioDto, HttpStatus.OK);
    }





}
