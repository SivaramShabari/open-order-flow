package com.openorderflow.order;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Table(name = "order")
public class Order {
    @Id
    private UUID orderId;

    @Column(name = "customer_id", nullable = false)
    private UUID customerId;

    @Column(name = "business_id", nullable = false)
    private UUID businessId;

    @Column(name = "business_outlet_id", nullable = false)
    private UUID businessOutletId;

    @Column(name = "delivery_partner_id", nullable = true)
    private UUID deliveryPartnerId;

    @JoinColumn(name = "order_status_id", foreignKey = @ForeignKey(name = "order_order_status"))
    @OneToOne
    private OrderStatus orderStatus;

    @Column(name = "payment_id", nullable = false)
    private UUID paymentId;

    @Column(name = "customer_delivery_address_id", nullable = false)
    private UUID deliveryAddressId;

    @Column(name = "coupon_id")
    private UUID appliedCouponId;

    private BigDecimal totalAmount;
    private BigDecimal totalDiscount;

    @Column(columnDefinition = "TEXT")
    private String customerInstructions;

    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @PrePersist
    @PreUpdate
    protected void updateTimestamp() {
        this.updatedAt = Instant.now();
    }

}
