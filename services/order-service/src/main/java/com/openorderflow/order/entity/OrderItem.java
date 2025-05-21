package com.openorderflow.order;

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
    private UUID orderItemId;

    @JoinColumn(name = "order_item_order_id", nullable = false)
    @ManyToOne(optional = false)
    private Order order;

    @Column(name = "business_item_id")
    private UUID businessItemId;

    @Column(name = "item_price_at_order")
    private BigDecimal itemPriceAtOrder;

    @Column(name = "item_discounted_price")
    private BigDecimal discountedItemPriceAtOrder;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderItemStatusEnum status;

    public enum OrderItemStatusEnum {
        BUSINESS_PACKED,
        BUSINESS_DELIVERED,
        PARTNER_PICKED,
        PARTNER_DELIVERED,
        PARTNER_DAMAGED,
        PARTNER_LOST,
        CUSTOMER_RECEIVED,
        CUSTOMER_RATED,
        CUSTOMER_RETURNED,
        BUSINESS_RECEIVED_RETURN
    }
}
