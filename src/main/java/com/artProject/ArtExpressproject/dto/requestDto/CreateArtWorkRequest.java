package com.artProject.ArtExpressproject.dto.requestDto;

import com.artProject.ArtExpressproject.model.Genre;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
public class CreateArtWorkRequest {
    private String name;

    private String description;

    private Long price;

    private Genre genre;

    private List<String> images;

    private boolean available;
    private Long artStudioId;


}
