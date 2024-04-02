package com.artProject.ArtExpressproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ArtStudio {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private User owner;

    private String businessName;

    private String description;

    @OneToOne
    private Address address;

    @Embedded
    private ContactInformation contactInformation;

    private boolean open;
    private String openingHours;


    @OneToMany(mappedBy = "artStudio",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orderList = new ArrayList<>();

    @ElementCollection
    @Column(length = 1000)
    private List<String> images;

    private LocalDateTime registrationDate = LocalDateTime.now();

    @JsonIgnore
    @OneToMany(mappedBy = "artStudio",cascade = CascadeType.ALL)
    private List<Artwork> artworks = new ArrayList<>();

}
