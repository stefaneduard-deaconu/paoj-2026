package com.pao.laboratory03.bonus.exception;
import com.pao.laboratory03.bonus.model.Status;

public class InvalidTransitionException extends RuntimeException {
    private final Status fromStatus;
    private final Status toStatus;

    public InvalidTransitionException(Status from, Status to) {
        super("Nu se poate trece din " + from + " in " + to);
        this.fromStatus = from;
        this.toStatus = to;
    }
}

