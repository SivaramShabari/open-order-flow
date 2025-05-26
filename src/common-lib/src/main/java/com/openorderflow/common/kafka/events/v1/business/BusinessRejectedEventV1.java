package com.openorderflow.common.kafka.events.v1.business;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class BusinessRejectedEventV1 {

    @NotNull
    private UUID orderId;

    @NotNull
    private UUID businessId;

    @NotNull
    private UUID businessOutletId;

    @NotNull
    private UUID rejectedByUserId;

    @NotNull
    private Instant rejectedAt;

    private String rejectionReason;
}
