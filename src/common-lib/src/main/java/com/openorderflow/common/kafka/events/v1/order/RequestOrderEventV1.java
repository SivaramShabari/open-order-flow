package com.openorderflow.common.kafka.events.v1.order;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class RequestOrderEventV1 {
    @NotNull
    private UUID orderId;

    @NotNull
    private UUID customerId;

    @NotNull
    private String customerName;

    @NotNull
    private String customerPhone;

    @NotNull
    private String customerEmail;

    @NotNull
    private UUID businessOutletId;

    @NotNull
    @Size(min = 1)
    private List<RequestedItem> items;

    @NotNull
    private UUID deliveryAddressId;

    @Size(max = 200)
    private String deliveryInstruction;

    private UUID couponId;

    @NotNull
    private PaymentDetails payment;

    @NotNull
    private Instant orderedAt;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder(toBuilder = true)
    public static class RequestedItem {
        @NotNull
        private UUID businessItemId;

        @Min(1)
        private int quantity;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder(toBuilder = true)
    public static class PaymentDetails {
        @NotNull
        private Boolean isPaid;

        @NotNull
        private String paymentMode;

        private UUID paymentId;
        private String paymentType;
        private Instant paidAt;
    }
}