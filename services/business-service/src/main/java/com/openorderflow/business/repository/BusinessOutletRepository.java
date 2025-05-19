package com.openorderflow.business.repository;

import com.openorderflow.business.entity.BusinessOutlet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BusinessOutletRepository extends JpaRepository<BusinessOutlet, UUID> {

}
