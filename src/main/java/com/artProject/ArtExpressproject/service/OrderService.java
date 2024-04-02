package com.artProject.ArtExpressproject.service;

import com.artProject.ArtExpressproject.dto.requestDto.OrderRequest;
import com.artProject.ArtExpressproject.model.Order;
import com.artProject.ArtExpressproject.model.User;

import java.util.List;

public interface OrderService {
    public Order createOrder(OrderRequest request, User user);

    public Order updateOrder(Long orderId, String orderStatus);

    public void calculateOrder(Long orderId);

    public List<Order> getUsersOrder(Long userId);

    public List<Order> getArtStudioOrder(Long artStudioId, String orderStatus);

    public Order findOrderById(Long orderId);


}
