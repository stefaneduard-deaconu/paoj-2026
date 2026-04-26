package com.pao.project.catalog.model;

// clasa imutabila
public final class IdentificatorScolar {
    private final String cod;

    public IdentificatorScolar(String cod) {
        this.cod = cod;
    }

    public String getCod() { return cod; }

    @Override
    public String toString() { return cod; }
}