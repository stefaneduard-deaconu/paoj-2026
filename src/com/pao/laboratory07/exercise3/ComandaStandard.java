package com.pao.laboratory07.exercise3;

import java.util.Locale;

public final class ComandaStandard extends Comanda {

    public ComandaStandard(String id, String client, double valoare) throws ComandaInvalidaException {
        super(id, client, valoare);
    }

    @Override
    public void procesare() {
        System.out.println("Procesare STANDARD finalizată pentru comanda: " + id);
    }

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