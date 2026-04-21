package com.pao.laboratory07.exercise1.exceptions;

public class OrderIsAlreadyFinalException extends RuntimeException {
    public OrderIsAlreadyFinalException(String message) {
        super(message);
    }
}
