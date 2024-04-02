package com.artProject.ArtExpressproject.dto.requestDto;

import lombok.Data;

@Data
public class CartItemRequest {
    private Long artworkId;
    private Long quantity;

}
