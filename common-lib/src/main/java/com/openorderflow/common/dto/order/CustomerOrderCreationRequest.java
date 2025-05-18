package com.openorderflow.common.dto.order;

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
public class CustomerOrderCreationRequest {

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