package com.artProject.ArtExpressproject.service;

import com.artProject.ArtExpressproject.customException.OrderException;
import com.artProject.ArtExpressproject.dto.requestDto.OrderRequest;
import com.artProject.ArtExpressproject.model.*;
import com.artProject.ArtExpressproject.repository.AddressRepository;
import com.artProject.ArtExpressproject.repository.OrderItemRepository;
import com.artProject.ArtExpressproject.repository.OrderRepository;
import com.artProject.ArtExpressproject.repository.UserRepository;
import com.artProject.ArtExpressproject.service.validationService.OrderVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ArtStudioService artStudioService;

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderVerificationService orderVerificationService;

@Override
public Order createOrder(OrderRequest request, User user) {
    Address shippingAddress = saveShippingAddress(request.getDeliveryAddress(), user);
    ArtStudio artStudio = artStudioService.findArtStudioById(request.getArtStudioId());
    Cart cart = cartService.findCartByUserId(user.getId());

    List<OrderItem> orderItems = createOrderItems(cart);
    Long totalPrice = cartService.calculateCartTotals(cart);

    Order createdOrder = buildOrder(user, shippingAddress, artStudio, orderItems, totalPrice);
    return orderRepository.save(createdOrder);
}

    private Address saveShippingAddress(Address shippingAddress, User user) {
        Address savedAddress = addressRepository.save(shippingAddress);
        if (!user.getAddress().contains(savedAddress)) {
            user.getAddress().add(savedAddress);
            userRepository.save(user);
        }
        return savedAddress;
    }

    private List<OrderItem> createOrderItems(Cart cart) {
        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem cartItem : cart.getItem()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setArtwork(cartItem.getArtwork());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setTotalPrice(cartItem.getTotalPrice());
            orderItems.add(orderItemRepository.save(orderItem));
        }
        return orderItems;
    }

    private Order buildOrder(User user, Address shippingAddress, ArtStudio artStudio, List<OrderItem> orderItems, Long totalPrice) {
        Order createdOrder = new Order();
        createdOrder.setCollector(user);
        createdOrder.setCreatedAt(LocalDate.now());
        createdOrder.setOrderStatus("PENDING");
        createdOrder.setDeliveryAddress(shippingAddress);
        createdOrder.setArtStudio(artStudio);
        createdOrder.setItems(orderItems);
        createdOrder.setTotalPrice(totalPrice);
        return createdOrder;
    }

    @Override
    public Order updateOrder(Long orderId, String orderStatus) {
        Order order = findOrderById(orderId);
        if(orderStatus.equals("OUT_FOR_DELIVERY") || orderStatus.equals("DELIVERED") || orderStatus.equals("COMPLETED") || orderStatus.equals("PENDING") ){
            order.setOrderStatus(orderStatus);
            return orderRepository.save(order);
        }
        throw new OrderException("Please select a valid order status");
    }

    @Override
    public void calculateOrder(Long orderId) {
        Order order = findOrderById(orderId);
        orderRepository.deleteById(orderId);

    }

    @Override
    public List<Order> getUsersOrder(Long userId) {
        return orderVerificationService.getUserOrder(userId);
    }

    @Override
    public List<Order> getArtStudioOrder(Long artStudioId, String orderStatus) {
        List<Order> orders = orderVerificationService.getArtStudioOrder(artStudioId);
        if(orderStatus != null){
            orders=orders.stream().filter(order->order.getOrderStatus().equals(orderStatus)).collect(Collectors.toList());
        }
        return orders;
    }

    @Override
    public Order findOrderById(Long orderId) {
        return orderVerificationService.findOrderById(orderId);
    }
}
