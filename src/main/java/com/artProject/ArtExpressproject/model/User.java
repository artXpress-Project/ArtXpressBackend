package com.artProject.ArtExpressproject.model;

import com.artProject.ArtExpressproject.dto.ArtStudioDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;


    private USER_ROLE role=USER_ROLE.ROLE_COLLECTOR;

    @JsonIgnore
    @OneToMany
    private List<Order> orders = new ArrayList<>();

    @ElementCollection
    private List<ArtStudioDto> favorites = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true )
    private List<Address> address = new ArrayList<>();

    private LocalDate date = LocalDate.now();

    private LocalTime time = LocalTime.now();


}
