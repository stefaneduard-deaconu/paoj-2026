package com.pao.project.catalog.model;

public class Nota {
    private int valoare;
    private String data;

    public Nota(int valoare, String data) {
        this.valoare = valoare;
        this.data = data;
    }

    public int getValoare() { return valoare; }
    public String getData() { return data; }
}