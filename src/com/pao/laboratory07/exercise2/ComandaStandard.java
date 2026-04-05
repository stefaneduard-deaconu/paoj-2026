package com.pao.laboratory07.exercise2;

import java.util.Locale;

public final class ComandaStandard extends Comanda {

    public ComandaStandard(String id, String client, double valoare) {
        super(id, client, valoare);
    }

    @Override
    public void procesare() {}

    @Override
    public String tipComanda() {
        return "STANDARD";
    }

    @Override
    public String obtineDetalii() {
        return String.format(Locale.US, "STANDARD: %s %s, valoare: %.2f lei", id, client, valoare);
    }

    @Override
    public void afiseaza() {
        System.out.println(obtineDetalii());
    }
}