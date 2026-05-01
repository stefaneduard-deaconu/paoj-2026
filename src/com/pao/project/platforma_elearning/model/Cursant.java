package com.pao.project.platforma_elearning.model;

public class Cursant extends Utilizator {
    private double portofelVirtual;

    public Cursant(String nume, String email, String parola, double portofel) {
        super(nume, email, parola);
        this.portofelVirtual = portofel;
    }

    public double getPortofelVirtual() {
        return portofelVirtual;
    }
    public void setPortofelVirtual(double portofelVirtual) {
        this.portofelVirtual = portofelVirtual;
    }

    @Override
    public String getTipUtilizator() {
        return "CURSANT";
    }

    @Override
    public String toString() {
        return "Cursant: " + super.toString() + ", portofel = " + portofelVirtual;
    }
}