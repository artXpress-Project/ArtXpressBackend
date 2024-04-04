package com.artProject.ArtExpressproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String reviewBody;

    @ManyToOne
    private User user;

    @ManyToOne
    private Artwork artwork;

//    public Review(String reviewBody) {
//        this.reviewBody = reviewBody;
//    }



}
