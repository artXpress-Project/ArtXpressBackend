package com.artProject.ArtExpressproject.controller;


import com.artProject.ArtExpressproject.model.Order;
import com.artProject.ArtExpressproject.model.User;
import com.artProject.ArtExpressproject.service.OrderService;
import com.artProject.ArtExpressproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/admin")
public class AdminOrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;


    @GetMapping("/order/artWork/{id}")
    public ResponseEntity<List<Order>> getOrderHistory(
            @PathVariable Long id,
            @RequestParam(required = false) String order_status,
            @RequestHeader("Authorization")String jwt){
        User user = userService.findUserByJwt(jwt);
        List<Order> orders = orderService.getArtStudioOrder(id,order_status);
        return new ResponseEntity<>(orders, HttpStatus.OK);

    }

    @PutMapping("/order/{orderId}/{orderStatus}")
    public ResponseEntity<Order> updateOrderStatus(
            @PathVariable Long orderId,
            @PathVariable String orderStatus,
            @RequestHeader("Authorization")String jwt){
        User user = userService.findUserByJwt(jwt);
        Order order = orderService.updateOrder(orderId,orderStatus);
        return new ResponseEntity<>(order, HttpStatus.OK);

    }

}
