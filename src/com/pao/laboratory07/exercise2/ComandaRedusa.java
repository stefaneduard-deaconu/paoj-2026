package com.pao.laboratory07.exercise2;

public final class ComandaRedusa extends Comanda{

    private double pret;
    private double discountProcent;

    public ComandaRedusa(String nume, double pret, double discount){
        super(nume);
        this.pret=pret;
        this.discountProcent=discount;

    }


    @Override
    public double pretFinal() {
        return pret * (1 - discountProcent / 100.0);
    }

    @Override
    public String descriere() {
        return String.format("DISCOUNTED: %s, pret: %.2f lei (-%d%%) [%s]", nume, pretFinal(), (int) discountProcent, stare_comanda.toString());
    }
}
