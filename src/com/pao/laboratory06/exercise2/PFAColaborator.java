package com.pao.laboratory06.exercise2;

import java.util.Scanner;

public class PFAColaborator extends Colaborator implements PersoanaFizica {
    private double cheltuieliLunare;

    @Override
    public void citeste(Scanner in) {
        super.citeste(in);
        this.cheltuieliLunare = in.nextDouble();
    }

    @Override
    public String tipContract() {
        return TipColaborator.PFA.name();
    }

    @Override
    public double calculeazaVenitNetAnual() {
        double venitNet = (venitBrutLunar - cheltuieliLunare) * 12;
        double impozit = 0.10 * venitNet;

        double salariuMinimLunar = 4050;
        double prag6 = 6 * salariuMinimLunar;
        double prag12 = 12 * salariuMinimLunar;
        double prag24 = 24 * salariuMinimLunar;
        double prag72 = 72 * salariuMinimLunar;
        double cass = 0;
        if (venitNet < prag6) {
            cass = 0.10 * prag6;
        } else if (venitNet <= prag72) {
            cass = 0.10 * venitNet;
        } else {
            cass = 0.10 * prag72;
        }

        double cas = 0;
        if (venitNet < prag12) {
            cas = 0;
        } else if (venitNet <= prag24) {
            cas = 0.25 * prag12;
        } else {
            cas = 0.25 * prag24;
        }

        return venitNet - impozit - cass - cas;
    }
}