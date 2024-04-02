package com.artProject.ArtExpressproject.dto.responseDto;

import com.artProject.ArtExpressproject.model.USER_ROLE;
import lombok.Data;

@Data
public class AuthResponse {
    private String jwt;
    private String message;
    private USER_ROLE user;

}
