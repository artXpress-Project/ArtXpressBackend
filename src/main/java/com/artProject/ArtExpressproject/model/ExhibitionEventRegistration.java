package com.artProject.ArtExpressproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "exhibition")
@AllArgsConstructor
@NoArgsConstructor
public class ExhibitionEventRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String secondName;
    @Column(nullable = false)
    private String email;
    @Column( nullable = false)
    private String phoneNumber;
    private LocalDate dateRegistered;
}
