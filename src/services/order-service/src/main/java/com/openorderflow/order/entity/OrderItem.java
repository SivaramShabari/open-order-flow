package com.openorderflow.order.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "order_items")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItem {
    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private UUID orderItemId;

    @JoinColumn(name = "order_item_order_id", nullable = false)
    @ManyToOne(optional = false)
    private Order order;

    @Embedded
    private BusinessItemSnapshot businessItem;
}
