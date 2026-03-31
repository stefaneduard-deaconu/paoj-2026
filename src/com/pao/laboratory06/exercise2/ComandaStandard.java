package com.pao.laboratory06.exercise2;

import java.util.Locale;

public class ComandaStandard extends Comanda {
    @Override
    public void afiseaza() {
        System.out.printf(Locale.US, "STANDARD: %s %s, valoare: %.2f lei\n", id, client, valoare);
    }

    @Override
    public String getTip() {
        return "STANDARD";
    }
}