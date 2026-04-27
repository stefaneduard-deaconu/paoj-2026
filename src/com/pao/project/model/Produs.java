package com.pao.project.model;

import java.util.Objects;

public class Produs {

    private int id;
    private String nume;
    private double pret;

    public Produs(int id, String nume, double pret) {
        this.id = id;
        this.nume = nume;
        this.pret = pret;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Produs produs = (Produs) o;
        return id == produs.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public double getPret() {
        return this.pret;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }



}