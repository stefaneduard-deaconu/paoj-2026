package com.pao.laboratory06.exercise2;

import java.util.Scanner;

public abstract class Comanda {
    protected String id;
    protected String client;
    protected double valoare;

    public void citeste(Scanner in) {
        this.id = in.next();
        this.client = in.next();
        this.valoare = in.nextDouble();
    }

    public abstract void afiseaza();

    public double getValoare() {
        return valoare;
    }

    public double getValoareSortare() {
        return valoare;
    }

    public abstract String getTip();
}