package com.pao.laboratory06.exercise2;

import java.util.Locale;
import java.util.Scanner;

public class ComandaAbonament extends Comanda implements ComandaSpeciala {
    private int luni;

    @Override
    public void citeste(Scanner in) {
        super.citeste(in);
        this.luni = in.nextInt();
    }

    @Override
    public double getValoareSortare() {
        return luni;
    }

    @Override
    public void afiseaza() {
        System.out.printf(Locale.US, "ABONAMENT: %s %s, valoare: %.2f lei, luni: %d\n", id, client, valoare, luni);
    }

    @Override
    public String getTip() {
        return "ABONAMENT";
    }

    public int getLuni(){
    return luni;
    }
}