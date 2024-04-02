package com.artProject.ArtExpressproject.service.validationService;

import com.artProject.ArtExpressproject.customException.NotFoundException;
import com.artProject.ArtExpressproject.model.Cart;
import com.artProject.ArtExpressproject.model.CartItem;
import com.artProject.ArtExpressproject.repository.CartItemRepository;
import com.artProject.ArtExpressproject.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Validated
@Service
public class CartVerification {
    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CartRepository cartRepository;

    public CartItem findCartItemById(Long id){
        Optional<CartItem> cartItemOptional = cartItemRepository.findById(id);
        if(cartItemOptional.isEmpty()) throw new NotFoundException("Cart item not found...");
        else return cartItemOptional.get();
    }

    public Cart findCartById(Long id){
        Optional<Cart> cart = cartRepository.findById(id);
        if(cart.isEmpty()) throw new NotFoundException("Cart not found");
        else return  cart.get();

    }

    public Cart findByUserId(Long userId){
        Cart cart = cartRepository.findByCollectorId(userId);
        if(cart == null) throw new NotFoundException("Cart not found by User");
        else return cart;

    }
}
