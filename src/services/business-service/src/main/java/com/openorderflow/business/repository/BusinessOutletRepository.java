package com.openorderflow.business.repository;

import com.openorderflow.business.entity.BusinessOutlet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BusinessOutletRepository extends JpaRepository<BusinessOutlet, UUID> {
    @Query("SELECT bo FROM BusinessOutlet bo " +
            "JOIN bo.business " +
            "WHERE city = :city AND state = :state")
    List<BusinessOutlet> findByCityAndState(@Param("city") String city, @Param("state") String state);
}
