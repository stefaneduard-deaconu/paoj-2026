package com.pao.laboratory07.exercise2;

import java.util.Locale;

public final class Precomanda extends Comanda {
    private String dataLivrare;

    public Precomanda(String id, String client, double valoare, String dataLivrare) {
        super(id, client, valoare);
        this.dataLivrare = dataLivrare;
    }

    @Override
    public void procesare() {}

    @Override
    public String tipComanda() {
        return "PRECOMANDA";
    }

    @Override
    public boolean esteSpeciala() {
        return true;
    }

    @Override
    public String obtineDetalii() {
        return String.format(Locale.US, "PRECOMANDA: %s %s, valoare: %.2f lei, livrare: %s", id, client, valoare, dataLivrare);
    }

    @Override
    public void afiseaza() {
        System.out.println(obtineDetalii());
    }
}