package com.pao.project.catalog.model;

public abstract class Angajat extends Persoana {
    protected double salariu;

    public Angajat(String nume, String email, double salariu) {
        super(nume, email);
        this.salariu = salariu;
    }

    public double getSalariu() { return salariu; }
    public void setSalariu(double salariu) { this.salariu = salariu; }
}