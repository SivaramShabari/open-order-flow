package com.openorderflow.common.kafka.events.v1;

public class InvalidEventTypeException extends RuntimeException {
    public InvalidEventTypeException(String message) {
        super(message);
    }
}
