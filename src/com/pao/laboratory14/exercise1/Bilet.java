package com.pao.laboratory14.exercise1;

public class Bilet {
    private final int id;
    private final String eveniment;
    private final TipBilet tip;
    private final double pret;

    public Bilet(int id, String eveniment, TipBilet tip, double pret) {
        this.id = id;
        this.eveniment = eveniment;
        this.tip = tip;
        this.pret = pret;
    }

    public int getId() { return id; }
    public String getEveniment() { return eveniment; }
    public TipBilet getTip() { return tip; }
    public double getPret() { return pret; }
}
