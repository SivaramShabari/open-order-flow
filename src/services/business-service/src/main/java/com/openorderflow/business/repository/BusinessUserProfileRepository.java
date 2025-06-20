package com.openorderflow.business.repository;

import com.openorderflow.business.entity.BusinessUserProfile;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BusinessUserProfileRepository extends JpaRepository<BusinessUserProfile, UUID> {
    Boolean existsByPhone(String phone);

    @EntityGraph(attributePaths = {"business", "businessOutlet"})
    BusinessUserProfile findByPhone(String phone);

    @Override
    @EntityGraph(attributePaths = {"business", "businessOutlet"})
    Optional<BusinessUserProfile> findById(UUID id);
}
