package com.pao.project.catalog.model;

public class Profesor extends Angajat {
    private String specializare;

    public Profesor(String nume, String email, double salariu, String specializare) {
        super(nume, email, salariu);
        this.specializare = specializare;
    }

    @Override
    public String getRol() { return "PROFESOR"; }

    public String getSpecializare() { return specializare; }
    public void setSpecializare(String specializare) { this.specializare = specializare; }
}