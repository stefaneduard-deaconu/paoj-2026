package com.pao.laboratory07.exercise2;

public final class ComandaGratuita extends Comanda{

//    private double pret;


    public ComandaGratuita(String nume){
        super(nume);
//        this.pret=pret;

    }

    @Override
    public double pretFinal() {
        return 0.0;
    }

    @Override
    public String descriere() {
        return String.format("GIFT: %s, gratuit [%s]", nume, stare_comanda.toString());
    }
}
