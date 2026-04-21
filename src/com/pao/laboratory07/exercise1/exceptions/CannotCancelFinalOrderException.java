package com.pao.laboratory07.exercise1.exceptions;

public class CannotCancelFinalOrderException extends RuntimeException {
    public CannotCancelFinalOrderException(String message) {
        super(message);
    }
}
