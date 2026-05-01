package com.pao.project.platforma_elearning.model;

public class Admin extends Staff {
    private int nivelAcces;

    public Admin(String nume, String email, String parola, double salariu, int nivelAcces) {
        super(nume, email, parola, salariu);
        this.nivelAcces = nivelAcces;
    }

    @Override
    public String getTipUtilizator() {
        return "ADMINISTRATOR";
    }

    @Override
    public String toString() {
        return "Admin: " + super.toString() + ", nivelul de acces = " + nivelAcces;
    }
}