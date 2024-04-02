package com.artProject.ArtExpressproject.repository;

import com.artProject.ArtExpressproject.model.ExhibitionEventRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExhibitionEventRegistrationRepository extends JpaRepository<ExhibitionEventRegistration,Long> {
    ExhibitionEventRegistration findByEmail(String email);

    Boolean existsByEmail(String email);


}
