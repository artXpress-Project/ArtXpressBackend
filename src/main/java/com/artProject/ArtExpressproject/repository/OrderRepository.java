package com.artProject.ArtExpressproject.repository;

import com.artProject.ArtExpressproject.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {


    List<Order> findByCollectorId(Long userId);

    List<Order> findByArtStudioId(Long artStudioId);
}
