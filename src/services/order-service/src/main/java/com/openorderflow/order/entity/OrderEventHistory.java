package com.openorderflow.order.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "order_event_history")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderEventHistory {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @JoinColumn(name = "order_id", foreignKey = @ForeignKey(name = "order_history_order_id"), nullable = false)
    @ManyToOne(cascade = CascadeType.DETACH)
    private Order order;

    @Column(name = "time_stamp", nullable = false)
    private Instant timestamp;

    @Column(name = "updated_by_id")
    private UUID updatedById;

    @Column(name = "updated_by")
    @Enumerated(EnumType.STRING)
    private OrderUpdationPersonaEnum updatedBy;

    private UUID statusId;
    @Column(columnDefinition = "TEXT")
    private String comments;

    public enum OrderUpdationPersonaEnum {
        CUSTOMER,
        BUSINESS,
        DELIVERY_PARTNER,
        PLATFORM_SUPPORT,
        SYSTEM
    }

}
