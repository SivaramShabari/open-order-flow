package com.openorderflow.common.dto.order;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record OrderCreationRequest(

        @NotNull
        UUID businessOutletId,

        @NotNull
        @Size(min = 1)
        List<RequestedItem> items,

        @NotNull
        UUID deliveryAddressId,

        @Size(max = 200)
        String deliveryInstruction,

        UUID couponId,  // optional

        @NotNull
        PaymentDetails payment

) {
    public record RequestedItem(
            @NotNull UUID businessItemId,
            @Min(1) int quantity
    ) {}

    public record PaymentDetails(
            @NotNull Boolean isPaid,

            @NotNull String paymentMode, // e.g. CARD, UPI, COD

            UUID paymentId, // optional if post delivery payment

            String paymentType, // optional: e.g. "prepaid", "pay_later"

            Instant paidAt // optional
    ) {}
}