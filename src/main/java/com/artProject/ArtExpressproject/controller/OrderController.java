package com.artProject.ArtExpressproject.controller;


import com.artProject.ArtExpressproject.dto.requestDto.CartItemRequest;
import com.artProject.ArtExpressproject.dto.requestDto.OrderRequest;
import com.artProject.ArtExpressproject.model.CartItem;
import com.artProject.ArtExpressproject.model.Order;
import com.artProject.ArtExpressproject.model.User;
import com.artProject.ArtExpressproject.service.OrderService;
import com.artProject.ArtExpressproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @PostMapping("/order")
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequest request,
                                                @RequestHeader("Authorization")String jwt){
        User user = userService.findUserByJwt(jwt);
        Order order = orderService.createOrder(request,user);
        return new ResponseEntity<>(order, HttpStatus.OK);

    }

    @GetMapping("/order/user")
    public ResponseEntity<List<Order>> getOrderHistory(
                                                      @RequestHeader("Authorization")String jwt){
        User user = userService.findUserByJwt(jwt);
        List<Order> orders = orderService.getUsersOrder(user.getId());
        return new ResponseEntity<>(orders, HttpStatus.OK);

    }
}
