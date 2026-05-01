package com.pao.laboratory07.exercise3;

public final class ComandaStandard extends Comanda {
    private double pret;

    public ComandaStandard(String nume, double pret, String client) {
        super(nume, client);
        this.pret = pret;
    }

    @Override
    public double pretFinal() { return pret; }

    @Override
    public String descriere() {
        return String.format("STANDARD: %s, pret: %.2f lei [%s] - client: %s", nume, pretFinal(), stare, client);
    }
}