package com.pao.laboratory06.exercise2.models;

import java.util.Scanner;

public class PFAColaborator extends PersoanaFizica {
    private double cheltuieliLunare;

    @Override
    public TipColaborator getTip() {
        return TipColaborator.PFA;
    }

    @Override
    public void citeste(Scanner in) {
        this.nume = in.next();
        this.prenume = in.next();
        this.venitBrutLunar = in.nextDouble();
        this.cheltuieliLunare = in.nextDouble();
    }

    @Override
    public double calculeazaVenitNetAnual() {
        double venitNetIntermediar = (venitBrutLunar - cheltuieliLunare) * 12;

        double prag6 = 6 * 4050.0 * 12, prag12 = 12 * 4050.0 * 12, prag24 = 24 * 4050.0 * 12, prag72 = 72 * 4050.0 * 12;
        double impozit = 0.10 * venitNetIntermediar;

        double cass = 0;
        if (venitNetIntermediar < prag6) {
            cass = 0.10 * prag6;
        } else if (venitNetIntermediar <= prag72) {
            cass = 0.10 * venitNetIntermediar;
        } else {
            cass = 0.10 * prag72;
        }

        double cas = 0;
        if (venitNetIntermediar >= prag12 && venitNetIntermediar < prag24) {
            cas = 0.25 * prag12;
        } else if (venitNetIntermediar >= prag24) {
            cas = 0.25 * prag24;
        }

        return venitNetIntermediar - impozit - cass - cas;
    }

    @Override
    public String tipContract() { return TipColaborator.PFA.name(); }
}