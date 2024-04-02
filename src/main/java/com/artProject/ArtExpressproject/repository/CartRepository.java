package com.artProject.ArtExpressproject.repository;

import com.artProject.ArtExpressproject.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {
    Cart findByCollectorId(Long userId);


}
