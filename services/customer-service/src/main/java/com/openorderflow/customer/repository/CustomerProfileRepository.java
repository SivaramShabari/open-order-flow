package com.openorderflow.customer.repository;

import com.openorderflow.customer.entity.CustomerProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomerProfileRepository extends JpaRepository<CustomerProfile, UUID> {
    Boolean existsByPrimaryPhoneNumber(String primaryPhoneNumber);
    Boolean existsByEmail(String email);
    CustomerProfile getByPrimaryPhoneNumber(String primaryPhoneNumber);
}
