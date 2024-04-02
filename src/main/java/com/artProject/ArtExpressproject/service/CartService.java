package com.artProject.ArtExpressproject.service;

import com.artProject.ArtExpressproject.dto.requestDto.CartItemRequest;
import com.artProject.ArtExpressproject.dto.requestDto.UpdateCartItemRequest;
import com.artProject.ArtExpressproject.model.Cart;
import com.artProject.ArtExpressproject.model.CartItem;

public interface CartService {
    CartItem addItemToCart(CartItemRequest request, String jwt);

    CartItem updateCartItemQuantity(Long CartItemId, Long quantity);

    Cart removeItemFromCart(Long cartItemId, String jwt);

    Long calculateCartTotals(Cart cart);

    Cart findCartById(Long id);

    Cart findCartByUserId(Long userId);

    Cart clearCart(Long userId);

}
