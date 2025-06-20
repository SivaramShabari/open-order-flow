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
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue
    @Column(name = "orderId")
    private UUID orderId;

    @Embedded
    private CustomerSnapshot customer;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "location.latitude", column = @Column(name = "delivery_location_latitude")),
            @AttributeOverride(name = "location.longitude", column = @Column(name = "delivery_location_longitude"))
    })
    private DeliveryLocation deliveryLocation;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "startingLocation.latitude", column = @Column(name = "delivery_partner_latitude")),
            @AttributeOverride(name = "startingLocation.longitude", column = @Column(name = "delivery_partner_longitude"))
    })
    private DeliveryPartnerSnapshot deliveryPartner;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "location.latitude", column = @Column(name = "business_outlet_latitude")),
            @AttributeOverride(name = "location.longitude", column = @Column(name = "business_outlet_longitude"))
    })
    private BusinessSnapshot business;

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
