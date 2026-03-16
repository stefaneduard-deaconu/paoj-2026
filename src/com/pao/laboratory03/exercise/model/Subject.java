package com.pao.laboratory03.exercise.model;

public enum Subject {
    PAOJ("Programare Avansata pe Obiecte", 6),
    BD("Baze de Date", 5),
    SO("Sisteme de Operare", 5),
    RC("Retele de Calculatoare", 4);

    private final String fullName;
    private final int credits;

    Subject(String fullName, int credits) {
        this.fullName = fullName;
        this.credits = credits;
    }

    public String getFullName() { return fullName; }
    public int getCredits() { return credits; }

    @Override
    public String toString() {
        return name() + " (" + fullName + ", " + credits + " credite)";
    }
}