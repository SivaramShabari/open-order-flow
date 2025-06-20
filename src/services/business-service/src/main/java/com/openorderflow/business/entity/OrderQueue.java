package com.openorderflow.business.entity;

import com.openorderflow.common.dto.inventory.BusinessItemDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Table(name = "order_queue")
public class OrderQueue {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private UUID orderId;

    @Column
    private UUID customerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_outlet_id", nullable = false)
    private BusinessOutlet businessOutlet;

    @Enumerated(EnumType.STRING)
    @NotNull
    private OrderBusinessStatusEnum status;

    @NotNull
    private BigDecimal orderAmount;

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;

    @OneToMany(mappedBy = "orderQueue", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderQueueItem> orderQueueItems = new ArrayList<>();

    public enum OrderBusinessStatusEnum {
        RECEIVED,
        APPROVED,
        REJECTED,
        PREPARED
    }
}

