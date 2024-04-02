package com.artProject.ArtExpressproject.repository;

import com.artProject.ArtExpressproject.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {
}
