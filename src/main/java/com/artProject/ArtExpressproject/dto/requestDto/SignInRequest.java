package com.artProject.ArtExpressproject.dto.requestDto;

import lombok.Data;

@Data
public class SignInRequest {
    private String email;
    private String password;
}
