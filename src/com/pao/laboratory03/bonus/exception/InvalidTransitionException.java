package com.pao.laboratory03.bonus.exception;

import com.pao.laboratory03.bonus.enums.Status;

public class InvalidTransitionException extends RuntimeException {
    Status fromStatus, toStatus;
    public InvalidTransitionException(String message) {
        super(message);
    }

    public InvalidTransitionException(Status fromStatus, Status toStatus) {
        super(String.format("Nu se poate trece de la %s la %s", fromStatus, toStatus));
        this.fromStatus = fromStatus;
        this.toStatus = toStatus;
    }

}
