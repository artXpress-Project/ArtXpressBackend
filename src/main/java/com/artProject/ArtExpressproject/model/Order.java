package com.artProject.ArtExpressproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User collector;

    @JsonIgnore
    @ManyToOne
    private ArtStudio artStudio;

    private Long totalAmount;

    private String OrderStatus;

    private LocalDate createdAt;

    private LocalTime time;

    @ManyToOne
    private Address deliveryAddress;

//    private Payment payment;

    @OneToMany
    private List<OrderItem> items;

    private Long totalItem;

    private Long totalPrice;
}
