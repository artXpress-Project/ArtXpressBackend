package com.artProject.ArtExpressproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Artwork {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;

    private Long price;

    @JoinColumn(nullable = false)
    @ManyToOne(cascade = CascadeType.REMOVE)
    private Genre genre;

    @Column(length = 1000)
    @ElementCollection
    private List<String> images;

    private boolean available;

    @ManyToOne
    private ArtStudio artStudio;

    private LocalDate date;

    private LocalTime time;




}
