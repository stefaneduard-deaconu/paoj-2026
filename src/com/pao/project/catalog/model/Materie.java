package com.pao.project.catalog.model;

public class Materie {
    private String numeMaterie;
    private int nrCredite;

    public Materie(String numeMaterie, int nrCredite) {
        this.numeMaterie = numeMaterie;
        this.nrCredite = nrCredite;
    }

    public String getNumeMaterie() { return numeMaterie; }
    public int getNrCredite() { return nrCredite; }
}