package com.pao.laboratory07.exercise3;

import java.util.Comparator;

public abstract sealed class Comanda implements ActiuneComanda, Comparable<Comanda>
        permits ComandaStandard, Precomanda, ComandaAbonament {

    protected String id;
    protected String client;
    protected double valoare;

    public Comanda(String id, String client, double valoare) throws ComandaInvalidaException {
        if (id == null || id.isBlank()) throw new ComandaInvalidaException("ID-ul nu poate fi gol!");
        if (client == null || client.isBlank()) throw new ComandaInvalidaException("Numele este obligatoriu!");
        if (valoare <= 0) throw new ComandaInvalidaException("Valoarea trebuie sa fie pozitiva!");

        this.id = id;
        this.client = client;
        this.valoare = valoare;
    }

    public double getValoare() { return valoare; }
    public String getId() { return id; }
    public String getClient() { return client; }

    public abstract void procesare();

    @Override
    public void proceseaza() {
        procesare();
    }

    @Override
    public int compareTo(Comanda alta) {
        return Double.compare(alta.valoare, this.valoare);
    }

    // Comparator custom definit direct în clasă
    public static final Comparator<Comanda> DUPA_CLIENT_SI_VALOARE = new Comparator<Comanda>() {
        @Override
        public int compare(Comanda c1, Comanda c2) {
            int comparareNume = c1.getClient().compareTo(c2.getClient());
            if (comparareNume != 0) {
                return comparareNume;
            } else {
                return Double.compare(c2.getValoare(), c1.getValoare());
            }
        }
    };
}