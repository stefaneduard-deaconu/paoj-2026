package com.pao.project.platforma_elearning.model;

public class Instructor extends Staff {
    private String specializare;
    private double venituriIncasate;

    public Instructor(String nume, String email, String parola, double salariu, String specializare) {
        super(nume, email, parola, salariu);
        this.specializare = specializare;
        this.venituriIncasate = 0.0;
    }

    @Override
    public String getTipUtilizator() {
        return "INSTRUCTOR";
    }

    @Override
    public String toString() {
        return "Instructor: " + super.toString() + ", specializare = " + specializare + ", venituri = " + venituriIncasate;
    }
}