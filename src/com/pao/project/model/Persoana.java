package com.pao.project.model;

public abstract class Persoana {

    protected int id;
    protected String nume;

    public Persoana(int id, String nume) {
        this.id = id;
        this.nume = nume;
    }

    public int getId() {
        return id;
    }

    public abstract String getRol();
}