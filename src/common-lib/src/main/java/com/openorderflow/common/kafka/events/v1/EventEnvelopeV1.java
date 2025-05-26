package com.openorderflow.common.kafka.events.v1;

import java.util.UUID;

public record EventEnvelopeV1(
        UUID eventId,
        EventTypeV1 eventType,
        UUID traceId
) {
}
