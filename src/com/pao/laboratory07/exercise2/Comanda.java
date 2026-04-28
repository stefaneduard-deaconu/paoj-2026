package com.pao.laboratory07.exercise2;
import com.pao.laboratory07.exercise1.StareComanda;


public abstract sealed class Comanda permits ComandaStandard, ComandaRedusa, ComandaGratuita   {
    protected String nume;
    protected StareComanda stare_comanda;


    Comanda(String nume){
        this.nume=nume;
        stare_comanda=StareComanda.PLACED;
    }


    public abstract double pretFinal();
    public abstract String descriere();
}