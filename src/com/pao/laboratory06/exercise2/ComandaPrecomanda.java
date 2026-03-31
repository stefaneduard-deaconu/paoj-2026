package com.pao.laboratory06.exercise2;

import java.util.Locale;
import java.util.Scanner;

public class ComandaPrecomanda extends Comanda implements ComandaSpeciala {
    private String dataLivrare;

    @Override
    public void citeste(Scanner in) {
        super.citeste(in);
        this.dataLivrare = in.next();
    }

    @Override
    public void afiseaza() {
        System.out.printf(Locale.US, "PRECOMANDA: %s %s, valoare: %.2f lei, livrare: %s\n", id, client, valoare, dataLivrare);
    }

    @Override
    public String getTip() {
        return "PRECOMANDA";
    }
}