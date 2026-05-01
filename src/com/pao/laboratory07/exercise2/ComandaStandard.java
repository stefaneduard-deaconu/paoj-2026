package com.pao.laboratory07.exercise2;

public final class ComandaStandard extends Comanda {
    private double pret;

    public ComandaStandard(String nume, double pret) {
        super(nume);
        this.pret = pret;
    }

    @Override
    public double pretFinal() {
        return pret;
    }

    @Override
    public String descriere() {
        return String.format("STANDARD: %s, pret: %.2f lei [%s]", nume, pretFinal(), stare);
    }
}