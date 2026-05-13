package com.pao.laboratory10.exercise1;

public class Tranzactie {
    private final int id;
    private final double suma;
    private final String data;
    private final TipTranzactie tip;

    public Tranzactie(int id, double suma, String data, TipTranzactie tip) {
        this.id = id;
        this.suma = suma;
        this.data = data;
        this.tip = tip;
    }

    public TipTranzactie getTip() {
        return tip;
    }
    public double getSuma() {
        return suma;
    }
    public int getId() { return id; }
    public String getData() { return data; }

    @Override
    public String toString() {
        return String.format("[%d] %s %s: %.2f RON", id, data, tip, suma);
    }
}