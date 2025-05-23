package com.openorderflow.common.dto.business;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderQueueActionRequestDto {

    @NotNull
    private UUID orderQueueId;

    @NotNull
    private UUID businessOutletId;

    @NotNull
    private Action action;

    private String rejectionReason; // optional if action = REJECTED

    public enum Action {
        APPROVED,
        REJECTED,
        PREPARED
    }
}

