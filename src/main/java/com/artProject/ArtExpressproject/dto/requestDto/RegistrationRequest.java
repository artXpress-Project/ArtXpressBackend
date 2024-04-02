package com.artProject.ArtExpressproject.dto.requestDto;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RegistrationRequest {
    private String firstName;
    private String secondName;
    private String email;
    private String phoneNumber;

}
