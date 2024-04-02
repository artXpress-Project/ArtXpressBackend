package com.artProject.ArtExpressproject.repository;

import com.artProject.ArtExpressproject.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
}
