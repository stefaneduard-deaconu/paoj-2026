package com.pao.laboratory03.exercise.model;

//1. model/Subject.java — ENUM
//- Constante: PAOJ, BD, SO, RC (sau alte materii)
//- Câmpuri: String fullName, int credits
//- Constructor privat, getteri
//- toString() → "PAOJ (Programare Avansată pe Obiecte, 6 credite)"

public enum Subject {
    PAOJ("Programare Avansata pe Obiecte in Java", 6){
        @Override
        public String toString(){
            return "PAOJ (" + getFullName() + ", " + getCredits() + " credite)";
        }
    },
    BD("Baze de Date", 4){
        @Override
        public String toString(){
            return "BD (" + getFullName() + ", " + getCredits() + " credite)";
        }
    },
    SO("Sisteme de Operare", 4){
        @Override
        public String toString(){
            return "SO (" + getFullName() + ", " + getCredits() + " credite)";
        }
    },
    RC("Retele de Calculatoare", 6){
        @Override
        public String toString(){
            return "RC (" + getFullName() + ", " + getCredits() + " credite)";
        }
    };

    public int getCredits(){
        return this.credits;
    }
    public String getFullName(){
        return this.fullName;
    }
    public abstract String toString();

    private String fullName;
    private int credits;

    Subject(String fullName, int credits){
        this.fullName = fullName;
        this.credits = credits;
    }
}

