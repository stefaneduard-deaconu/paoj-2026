package com.pao.laboratory06.exercise2;

import java.util.Scanner;

public class CIMColaborator extends Colaborator implements PersoanaFizica {
    private boolean bonus;

    @Override
    public void citeste(Scanner in) {
        super.citeste(in);
        String areB = in.next();
        this.bonus = areB.equalsIgnoreCase("DA");
    }

    @Override
    public String tipContract() {
        return TipColaborator.CIM.name();
    }

    @Override
    public boolean areBonus() {
        return bonus;
    }

    @Override
    public double calculeazaVenitNetAnual() {
        double netAnual = venitBrutLunar * 12 * 0.55;
        if (areBonus()) {
            netAnual += netAnual * 0.10;
        }
        return netAnual;
    }
}