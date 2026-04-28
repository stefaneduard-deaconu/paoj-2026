package com.pao.project.model;

public abstract class Angajat extends Persoana {

    private double salariu;
    private int ani_experienta;

    public Angajat(int id, String nume) {
        super(id, nume);
    }

    public double getSalariu() {
        return salariu;
    }

    public void setSalariu(double salariu) {
        this.salariu = salariu;
    }

    public int getAni_experienta() {
        return ani_experienta;
    }

    public void setAni_experienta(int ani_experienta) {
        this.ani_experienta = ani_experienta;
    }
}


