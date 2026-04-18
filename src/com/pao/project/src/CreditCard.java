package com.pao.project.src;

public class CreditCard extends Card {
    private double limitaCredit;
    private double dobandaPenalizatoare;
    private int puncteBonus;

    public CreditCard(String numarCard, String numePosesor, String dataExpirare, String pin,
                      double limitaCredit, double dobanda) {
        super(numarCard, numePosesor, dataExpirare, pin);
        this.limitaCredit = limitaCredit;
        this.dobandaPenalizatoare = dobanda;
        this.puncteBonus = 0;
    }

    public double getLimitaCredit() { return limitaCredit; }
    public int getPuncteBonus() { return puncteBonus; }

    public void adaugaPuncte(int puncte) {
        this.puncteBonus += puncte;
    }

    @Override
    public void afiseazaTipCard() {
        System.out.println("CARD CREDIT | Limita credit: " + limitaCredit + " | Puncte bonus: " + puncteBonus);
    }
}