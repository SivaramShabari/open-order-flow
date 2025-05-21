package com.openorderflow.order.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
    @GeneratedValue
    @Column(name = "id")
    private UUID orderId;

    @Embedded
    private CustomerSnapshot customer;

    @Embedded
    private BusinessSnapshot business;

    @Embedded
    private DeliveryPartnerSnapshot deliveryPartner;

    @Embedded
    private DeliveryLocation deliveryLocation;

    @Column(name = "order_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatusEnum orderStatus;

    @Column(name = "is_paid", nullable = false)
    private Boolean isPaid = false;

    @Column(name = "payment_id", nullable = true)
    private UUID paymentId;

    @Column(name = "coupon_id")
    private UUID appliedCouponId;

    @Column(name = "total_amount", nullable = false)
    private BigDecimal totalAmount;

    @Column(name = "discounted_amount")
    private BigDecimal discountedAmount;

    @Column(columnDefinition = "TEXT")
    private String customerInstructions;

    @CreationTimestamp
    @Column(updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;

}
