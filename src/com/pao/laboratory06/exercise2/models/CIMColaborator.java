package com.pao.laboratory06.exercise2.models;

import java.util.Scanner;

public class CIMColaborator extends PersoanaFizica {
    private boolean bonus;

    @Override
    public TipColaborator getTip() {
        return TipColaborator.CIM;
    }

    @Override
    public void citeste(Scanner in) {
        this.nume = in.next();
        this.prenume = in.next();
        this.venitBrutLunar = in.nextDouble();
        if (in.hasNext()) {
            String b = in.next();
            this.bonus = b.equalsIgnoreCase("DA");
        }
    }

    @Override
    public double calculeazaVenitNetAnual() {
        double net = venitBrutLunar * 12 * 0.55;
        if (bonus) net *= 1.10;
        return net;
    }

    @Override
    public String tipContract() { return TipColaborator.CIM.name(); }

    @Override
    public boolean areBonus() { return bonus; }
}