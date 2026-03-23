package com.pao.laboratory03.bonus.enums;

public enum Status {
    TODO {
        @Override
        public boolean canTransitionTo(Status next) {
            return next == IN_PROGRESS || next == CANCELLED;
        }
    },
    IN_PROGRESS {
        @Override
        public boolean canTransitionTo(Status next) {
            return next == DONE || next == CANCELLED;
        }
    },
    DONE {
        @Override
        public boolean canTransitionTo(Status next) {
            return false; // Nu poate trece nicăieri
        }
    },
    CANCELLED {
        @Override
        public boolean canTransitionTo(Status next) {
            return false; // Nu poate trece nicăieri
        }
    };

    public abstract boolean canTransitionTo(Status next);
}
