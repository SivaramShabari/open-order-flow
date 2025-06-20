package com.openorderflow.inventory.repository;


import com.openorderflow.inventory.entity.BusinessItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BusinessItemRepository extends JpaRepository<BusinessItem, UUID> {
    List<BusinessItem> findAllByIdIn(List<UUID> ids);
    List<BusinessItem> findAllByBusinessId(UUID businessId);
}
