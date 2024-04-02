package com.artProject.ArtExpressproject.dto.requestDto;

import com.artProject.ArtExpressproject.model.USER_ROLE;
import lombok.Data;

@Data
public class UserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
    private USER_ROLE role;

}
