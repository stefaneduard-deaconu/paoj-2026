package com.pao.project.src;

public class DebitCard extends Card {
    private CurrentAccount contAsociat;
    private double limitaZilnicaRetragere;

    public DebitCard(String numarCard, String numePosesor, String dataExpirare, String pin,
                     CurrentAccount cont, double limitaZilnica) {
        super(numarCard, numePosesor, dataExpirare, pin);
        this.contAsociat = cont;
        this.limitaZilnicaRetragere = limitaZilnica;
    }

    public CurrentAccount getContAsociat() { return contAsociat; }
    public double getLimitaZilnicaRetragere() { return limitaZilnicaRetragere; }

    @Override
    public void afiseazaTipCard() {
        System.out.println("CARD DEBIT | Legat de contul: " + contAsociat.getIban());
    }
}