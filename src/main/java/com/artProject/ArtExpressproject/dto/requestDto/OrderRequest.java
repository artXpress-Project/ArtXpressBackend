package com.artProject.ArtExpressproject.dto.requestDto;

import com.artProject.ArtExpressproject.model.Address;
import lombok.Data;

@Data
public class OrderRequest {
    private Long artStudioId;
    private Address deliveryAddress;


}
