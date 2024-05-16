package com.artProject.ArtExpressproject.controller;

import com.artProject.ArtExpressproject.dto.requestDto.CreateArtWorkRequest;
import com.artProject.ArtExpressproject.dto.responseDto.MessageResponse;
import com.artProject.ArtExpressproject.model.ArtStudio;
import com.artProject.ArtExpressproject.model.Artwork;
import com.artProject.ArtExpressproject.model.User;
import com.artProject.ArtExpressproject.service.ArtStudioService;
import com.artProject.ArtExpressproject.service.ArtWorkService;
import com.artProject.ArtExpressproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/artwork")
public class AdminArtworkController {
    @Autowired
    private ArtWorkService artWorkService;

    @Autowired
    private UserService userService;

    @Autowired
    private ArtStudioService artStudioService;

    @PostMapping()
    public ResponseEntity<Artwork> createArtwork(@RequestBody CreateArtWorkRequest request,
                                                 @RequestHeader("Authorization") String jwt){
        User user = userService.findUserByJwt(jwt);
        ArtStudio artStudio = artStudioService.findArtStudioById(user.getId());
        Artwork artwork = artWorkService.createArtwork(request,request.getGenre(),artStudio);
        return new ResponseEntity<>(artwork, HttpStatus.CREATED);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteArtwork(@PathVariable Long id,
                                                         @RequestHeader("Authorization") String jwt){
        User user = userService.findUserByJwt(jwt);
        artWorkService.deleteArtwork(id);
        MessageResponse message = new MessageResponse();
        message.setMessage("Artwork Deleted successfully");
        return new ResponseEntity<>(message, HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Artwork> updateArtworkAvailability(@PathVariable Long id,
                                                         @RequestHeader("Authorization") String jwt){
        User user = userService.findUserByJwt(jwt);
        Artwork artwork = artWorkService.updateAvailabilityStatus(id);
        return new ResponseEntity<>(artwork, HttpStatus.CREATED);

    }
}
