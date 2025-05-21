package com.openorderflow.common.kafka.events.v1.order;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class OrderPlacedEventV1 {

    @NotNull
    private UUID orderId;

    @NotNull
    private Instant placedAt;

    @NotNull
    private CustomerSnapshot customer;

    @NotNull
    private BusinessSnapshot business;

    @NotNull
    private DeliveryLocation deliveryLocation;

    @NotNull
    @Size(min = 1)
    private List<OrderItemSnapshot> items;

    @NotNull
    private Boolean isPaid;

    private UUID paymentId;
    private String paymentMode;
    private String paymentType;
    private Instant paidAt;

    private UUID appliedCouponId;
    private BigDecimal totalAmount;
    private BigDecimal discountedAmount;

    @Size(max = 200)
    private String customerInstructions;

    @NotNull
    private UUID traceId;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder(toBuilder = true)
    public static class CustomerSnapshot {
        private UUID id;
        private String name;
        private String email;
        private String phone;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder(toBuilder = true)
    public static class BusinessSnapshot {
        private UUID id;
        private UUID outletId;
        private String name;
        private String outletName;
        private String outletPhone;
        private String outletAddress;

        private GeoLocation location;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder(toBuilder = true)
    public static class DeliveryLocation {
        private String name;
        private String type;
        private String addressLine1;
        private String addressLine2;
        private String addressLine3;
        private String city;
        private String state;
        private GeoLocation location;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder(toBuilder = true)
    public static class OrderItemSnapshot {
        private UUID id;
        private String itemName;
        private String itemDescription;
        private String type;
        private BigDecimal priceAtOrder;
        private BigDecimal discountedPriceAtOrder;
        private int quantity;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder(toBuilder = true)
    public static class GeoLocation {
        private Double latitude;
        private Double longitude;
    }
}
