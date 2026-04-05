package com.pao.laboratory07.exercise2;

public abstract sealed class Comanda implements ActiuneComanda, Comparable<Comanda>
        permits ComandaStandard, Precomanda, ComandaAbonament {

    protected String id;
    protected String client;
    protected double valoare;

    public Comanda(String id, String client, double valoare) {
        this.id = id;
        this.client = client;
        this.valoare = valoare;
    }

    public double getValoare() { return valoare; }
    public String getId() { return id; }

    public abstract void procesare();

    @Override
    public void proceseaza() {
        procesare();
    }

    @Override
    public int compareTo(Comanda alta) {
        return Double.compare(alta.valoare, this.valoare);
    }
}