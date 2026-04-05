package com.pao.laboratory07.exercise2;

import java.util.Locale;

public final class ComandaAbonament extends Comanda {
    private int nrLuni;

    public ComandaAbonament(String id, String client, double valoare, int nrLuni) {
        super(id, client, valoare);
        this.nrLuni = nrLuni;
    }

    @Override
    public void procesare() {}

    @Override
    public String tipComanda() {
        return "ABONAMENT";
    }

    @Override
    public boolean esteSpeciala() {
        return true;
    }

    @Override
    public String obtineDetalii() {
        return String.format(Locale.US, "ABONAMENT: %s %s, valoare: %.2f lei, luni: %d", id, client, valoare, nrLuni);
    }

    @Override
    public void afiseaza() {
        System.out.println(obtineDetalii());
    }
}