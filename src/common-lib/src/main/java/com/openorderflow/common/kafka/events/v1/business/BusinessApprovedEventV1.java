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
public class BusinessApprovedEventV1 {

    @NotNull
    private UUID orderId;

    @NotNull
    private UUID businessId;

    @NotNull
    private UUID businessOutletId;

    @NotNull
    private UUID approvedByUserId;

    @NotNull
    private Instant approvedAt;

    private String approvalNote;
}
