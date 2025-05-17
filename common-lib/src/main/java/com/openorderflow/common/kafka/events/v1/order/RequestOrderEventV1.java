package com.openorderflow.common.kafka.events.v1.order;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record RequestOrderEventV1(

        @NotNull
        UUID customerId,

        @NotNull
        String customerName,

        @NotNull
        String customerPhone,

        @NotNull
        String customerEmail,

        @NotNull
        UUID businessOutletId,

        @NotNull
        @Size(min = 1)
        List<RequestedItem> items,

        @NotNull
        UUID deliveryAddressId,

        @Size(max = 200)
        String deliveryInstruction,

        UUID couponId,

        @NotNull
        PaymentDetails payment,

        @NotNull
        Instant orderedAt

) {
    public record RequestedItem(

            @NotNull UUID businessItemId,

            @Min(1) int quantity
    ) {}

    public record PaymentDetails(

            @NotNull Boolean isPaid,

            @NotNull String paymentMode,

            UUID paymentId,

            String paymentType,

            Instant paidAt // optional
    ) {}
}