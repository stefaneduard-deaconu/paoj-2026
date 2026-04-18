package com.pao.project.src;

public class Loan {
    private static int contorId = 500;

    private final int idLoan;
    private double sumaImprumutata;
    private double soldRamas; // Cât mai are clientul de plată
    private double rataLunara;
    private int perioadaLuni;
    private double dobandaAnuala;

    public Loan(double sumaImprumutata, int perioadaLuni, double dobandaAnuala) {
        this.idLoan = contorId++;
        this.sumaImprumutata = sumaImprumutata;
        this.perioadaLuni = perioadaLuni;
        this.dobandaAnuala = dobandaAnuala;

        double dobandaTotala = sumaImprumutata * (dobandaAnuala / 100) * (perioadaLuni / 12.0);
        this.soldRamas = sumaImprumutata + dobandaTotala;
        this.rataLunara = this.soldRamas / perioadaLuni;
    }

    public int getIdLoan() { return idLoan; }
    public double getSoldRamas() { return soldRamas; }
    public double getRataLunara() { return rataLunara; }

    public void platesteRata() {
        if (soldRamas >= rataLunara) {
            soldRamas -= rataLunara;
            System.out.println("Rata de " + rataLunara + " a fost platita. Sold ramas: " + soldRamas);
        } else {
            soldRamas = 0;
            System.out.println("Creditul a fost achitat integral!");
        }
    }

    @Override
    public String toString() {
        return "Credit ID: " + idLoan + " | Sold ramas: " + String.format("%.2f", soldRamas);
    }
}