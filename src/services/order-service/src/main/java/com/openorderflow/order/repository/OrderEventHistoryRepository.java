package com.openorderflow.order.repository;

import com.openorderflow.order.entity.OrderEventHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderEventHistoryRepository extends JpaRepository<OrderEventHistory, UUID> {
}
