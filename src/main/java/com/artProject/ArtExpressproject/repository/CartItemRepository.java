package com.artProject.ArtExpressproject.repository;

import com.artProject.ArtExpressproject.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

}
