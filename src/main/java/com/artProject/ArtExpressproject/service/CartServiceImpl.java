package com.artProject.ArtExpressproject.service;

import com.artProject.ArtExpressproject.dto.requestDto.CartItemRequest;
import com.artProject.ArtExpressproject.model.Artwork;
import com.artProject.ArtExpressproject.model.Cart;
import com.artProject.ArtExpressproject.model.CartItem;
import com.artProject.ArtExpressproject.model.User;
import com.artProject.ArtExpressproject.repository.CartItemRepository;
import com.artProject.ArtExpressproject.repository.CartRepository;
import com.artProject.ArtExpressproject.service.validationService.CartVerification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ArtWorkService artworkService;

    @Autowired
    private CartVerification cartVerification;
//    @Override
//    public CartItem addItemToCart(CartItemRequest request, String jwt) {
//        User user = userService.findUserByJwt(jwt);
//        Artwork artwork = artworkService.findArtworkById(request.getArtworkId());
//        Cart cart = cartRepository.findByCollectorId(user.getId());
//
//        for(CartItem cartItem: cart.getItem()) {
//            if (cartItem.getArtwork().equals(artwork)) {
//                Long newQuantity = cartItem.getQuantity() + request.getQuantity();
//                return  updateCartItemQuantity(cartItem.getId(), newQuantity);
//
//            }
//        }
//        CartItem cartItem = new CartItem();
//        cartItem.setArtwork(artwork);
//        cartItem.setCart(cart);
//        cartItem.setQuantity(request.getQuantity());
//        cartItem.setTotalPrice(request.getQuantity()*artwork.getPrice());
//        CartItem savedCartItem = cartItemRepository.save(cartItem);
//        cart.getItem().add(savedCartItem);
//
//
//        return savedCartItem;
//    }
        @Override
        public CartItem addItemToCart(CartItemRequest request, String jwt) {
        User user = userService.findUserByJwt(jwt);
        Artwork artwork = artworkService.findArtworkById(request.getArtworkId());
        Cart cart = cartRepository.findByCollectorId(user.getId());

        CartItem existingCartItem = findCartItemByArtwork(cart, artwork);
        if (existingCartItem != null) {
            return updateCartItemQuantity(existingCartItem.getId(), existingCartItem.getQuantity() + request.getQuantity());}

        CartItem cartItem = createCartItem(artwork, cart, request.getQuantity());
        return cartItemRepository.save(cartItem);
        }

        private CartItem findCartItemByArtwork(Cart cart, Artwork artwork) {
            for (CartItem cartItem : cart.getItem()) {
                if (cartItem.getArtwork().equals(artwork)) {
                    return cartItem;
                }
            }
            return null;
        }

        private CartItem createCartItem(Artwork artwork, Cart cart, Long quantity) {
            CartItem cartItem = new CartItem();
            cartItem.setArtwork(artwork);
            cartItem.setCart(cart);
            cartItem.setQuantity(quantity);
            cartItem.setTotalPrice(quantity * artwork.getPrice());
            cart.getItem().add(cartItem);
            return cartItem;
        }


    @Override
    public CartItem updateCartItemQuantity(Long cartItemId, Long quantity) {
        CartItem cartItem = cartVerification.findCartItemById(cartItemId);
        cartItem.setQuantity(quantity);
        cartItem.setTotalPrice(cartItem.getArtwork().getPrice()*quantity);

        return cartItemRepository.save(cartItem);
    }

    @Override
    public Cart removeItemFromCart(Long cartItemId, String jwt) {
        User user = userService.findUserByJwt(jwt);
        Cart cart = cartRepository.findByCollectorId(user.getId());
        CartItem cartItem = cartVerification.findCartItemById(cartItemId);
        cart.getItem().remove(cartItem);


        return cartRepository.save(cart);
    }

    @Override
    public Long calculateCartTotals(Cart cart) {
        long total = 0L;
        for(CartItem cartItem: cart.getItem()){
            total+=cartItem.getArtwork().getPrice()*cartItem.getQuantity();
        }
        return total;
    }

    @Override
    public Cart findCartById(Long id) {

        return cartVerification.findCartById(id);
    }

    @Override
    public Cart findCartByUserId(Long userId) {
//        User user = userService.findUserByJwt(jwt);
        Cart cart = cartVerification.findByUserId(userId);
        cart.setTotal(calculateCartTotals(cart));
        return cart;
    }

    @Override
    public Cart clearCart(Long userId) {
//        User user = userService.findUserByJwt(jwt);
        Cart cart = findCartByUserId(userId);
        cart.getItem().clear();
        return cartRepository.save(cart);
    }
}
