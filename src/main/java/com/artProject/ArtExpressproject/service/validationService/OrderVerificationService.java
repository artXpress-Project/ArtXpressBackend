package com.artProject.ArtExpressproject.service.validationService;

import com.artProject.ArtExpressproject.customException.NotFoundException;
import com.artProject.ArtExpressproject.model.Order;
import com.artProject.ArtExpressproject.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Service
@Validated
public class OrderVerificationService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getUserOrder(Long userId){
        List<Order> orders = orderRepository.findByCollectorId(userId);
        if(orders.isEmpty()) throw new NotFoundException("Orders not found");
        else return orders;

    }

    public List<Order> getArtStudioOrder(Long artStudioId){
        List<Order> orders = orderRepository.findByArtStudioId(artStudioId);
        if(orders.isEmpty()) throw new NotFoundException("No orders made yet");
        else return orders;

    }

    public Order findOrderById(Long orderId){
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if(optionalOrder.isEmpty()) throw new NotFoundException("Order not found");
        else return optionalOrder.get();
    }
}
