package com.artProject.ArtExpressproject.dto.requestDto;

import com.artProject.ArtExpressproject.model.Address;
import com.artProject.ArtExpressproject.model.ContactInformation;
import lombok.Data;

import java.util.List;

@Data
public class CreateArtStudioRequest {
    private Long id;
    private String businessName;
    private String description;
    private Address address;
    private ContactInformation contactInformation;
    private String openingHours;
    private List<String> images;


}
