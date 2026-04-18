package com.pao.laboratory07.exercise3;

import com.pao.laboratory07.exercise1.OrderState; // Importăm starea din Ex 1

public abstract sealed class Comanda permits ComandaStandard, ComandaRedusa, ComandaGratuita {
    protected String nume;
    protected String client; // Câmpul nou adăugat
    protected OrderState stare;

    public Comanda(String nume, String client) {
        this.nume = nume;
        this.client = client;
        this.stare = OrderState.PLACED;
    }

    public abstract double pretFinal();
    public abstract String descriere();

    // Getter esențial pentru metoda de sortare din Main
    public String getClient() {
        return client;
    }
}