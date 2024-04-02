package com.artProject.ArtExpressproject.controller;

import com.artProject.ArtExpressproject.dto.requestDto.CreateArtStudioRequest;
import com.artProject.ArtExpressproject.dto.responseDto.MessageResponse;
import com.artProject.ArtExpressproject.model.ArtStudio;
import com.artProject.ArtExpressproject.model.User;
import com.artProject.ArtExpressproject.service.ArtStudioService;
import com.artProject.ArtExpressproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/artStudio")
public class AdminArtStudioController {
    @Autowired
    private ArtStudioService artStudioService;

    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<ArtStudio> createArtStudio(@RequestBody CreateArtStudioRequest request,
                                                    @RequestHeader("Authorization") String jwt){
        User user = userService.findUserByJwt(jwt);
        ArtStudio artStudio = artStudioService.createArtStudio(request,user);
        return new ResponseEntity<>(artStudio, HttpStatus.CREATED);
    }

    @PutMapping ("/{id}")
    public ResponseEntity<ArtStudio> updateArtStudio(@RequestBody CreateArtStudioRequest request,
                                                     @RequestHeader("Authorization") String jwt,
                                                     @PathVariable Long id){
        User user = userService.findUserByJwt(jwt);
        ArtStudio artStudio = artStudioService.updateArtStudio(id,request);
        return new ResponseEntity<>(artStudio, HttpStatus.CREATED);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<MessageResponse> deleteArtStudio(
                                                     @RequestHeader("Authorization") String jwt,
                                                     @PathVariable Long id){
        User user = userService.findUserByJwt(jwt);
        artStudioService.deleteArtStudio(id);
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setMessage("ArtStudio Deleted Successfully");
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

    @PutMapping ("/{id}/status")
    public ResponseEntity<ArtStudio> updateArtStudioStatus(
                                                     @RequestHeader("Authorization") String jwt,
                                                     @PathVariable Long id){
        User user = userService.findUserByJwt(jwt);
        ArtStudio artStudio = artStudioService.updateRestaurantStatus(id);
        return new ResponseEntity<>(artStudio, HttpStatus.OK);
    }

    @GetMapping ("/user")
    public ResponseEntity<ArtStudio> findArtStudioByOwnerId(
            @RequestHeader("Authorization") String jwt
            ){
        User user = userService.findUserByJwt(jwt);
        ArtStudio artStudio = artStudioService.getArtStudioByUserId(user.getId());
        return new ResponseEntity<>(artStudio, HttpStatus.OK);
    }



}
