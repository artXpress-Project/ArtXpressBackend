package com.artProject.ArtExpressproject.dto.requestDto;

import lombok.Data;

@Data
public class UpdateCartItemRequest {
    private Long cartItemId;
    private Long quantity;
}
