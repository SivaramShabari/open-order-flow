package com.openorderflow.business.repository;

import com.openorderflow.business.entity.OrderQueue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderQueueRepository extends JpaRepository<OrderQueue, UUID> {

    // Get all orders by business outlet (for dashboard or approval queue)
    List<OrderQueue> findByBusinessOutletId(UUID businessOutletId);

    // Get orders by outlet and status (e.g., show all PENDING)
    List<OrderQueue> findByBusinessOutletIdAndStatus(UUID businessOutletId, OrderQueue.OrderBusinessStatusEnum status);

    // Get all PENDING orders across all outlets (for internal tools or batch processing)
    List<OrderQueue> findByStatus(OrderQueue.OrderBusinessStatusEnum status);

    // Optional: Get latest N PENDING orders for a business outlet
    List<OrderQueue> findTop10ByBusinessOutletIdAndStatusOrderByCreatedAtDesc(UUID businessOutletId, OrderQueue.OrderBusinessStatusEnum status);

    // Count PENDING orders for an outlet (for badges, dashboards)
    long countByBusinessOutletIdAndStatus(UUID businessOutletId, OrderQueue.OrderBusinessStatusEnum status);

    // Find by orderId (useful when resolving duplicates or tracking order life)
    Optional<OrderQueue> findByOrderId(UUID orderId);
}