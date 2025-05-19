package com.openorderflow.inventory.repository;


import com.openorderflow.inventory.entity.BusinessItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BusinessItemRepository extends JpaRepository<BusinessItem, UUID> {
}
