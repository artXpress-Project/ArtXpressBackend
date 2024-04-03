package com.artProject.ArtExpressproject.repository;

import com.artProject.ArtExpressproject.model.ArtStudio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArtStudioRepository extends JpaRepository<ArtStudio,Long> {
    ArtStudio findByOwnerId(Long id);

    @Query("SELECT a FROM ArtStudio a WHERE lower(a.owner.firstName) like lower(concat('%',:query,'%') ) " +
            "OR lower(a.businessName) LIKE lower(concat('%',:query, '%') ) " + "OR lower(a.owner.lastName) LIKE lower(concat('%',:query, '%') ) ")
    List<ArtStudio> findBySearchQuery(String query);

    ArtStudio findByBusinessName(String name);
}
