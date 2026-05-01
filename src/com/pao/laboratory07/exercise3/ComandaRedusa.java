package com.pao.laboratory07.exercise3;

public final class ComandaRedusa extends Comanda {
    private double pret;
    private int discountProcent;

    public ComandaRedusa(String nume, double pret, int discountProcent, String client) {
        super(nume, client);
        this.pret = pret;
        this.discountProcent = discountProcent;
    }

    public int getDiscountProcent() { return discountProcent; }

    @Override
    public double pretFinal() { return pret * (1 - discountProcent / 100.0); }

    @Override
    public String descriere() {
        return String.format("DISCOUNTED: %s, pret: %.2f lei (-%d%%) [%s] - client: %s", nume, pretFinal(), discountProcent, stare, client);
    }
}