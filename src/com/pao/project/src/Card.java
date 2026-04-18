package com.pao.project.src;

public abstract class Card {
    protected String numarCard;
    protected String numePosesor;
    protected String dataExpirare;
    private String pin;
    private boolean esteActiv;

    public Card(String numarCard, String numePosesor, String dataExpirare, String pin) {
        this.numarCard = numarCard;
        this.numePosesor = numePosesor;
        this.dataExpirare = dataExpirare;
        this.pin = pin;
        this.esteActiv = true;
    }

    public String getNumarCard() { return numarCard; }
    public String getNumePosesor() { return numePosesor; }
    public boolean isEsteActiv() { return esteActiv; }

    public void setEsteActiv(boolean esteActiv) {
        this.esteActiv = esteActiv;
    }

    public void schimbaPin(String pinVechi, String pinNou) {
        if (this.pin.equals(pinVechi)) {
            this.pin = pinNou;
            System.out.println("PIN schimbat cu succes!");
        } else {
            System.out.println("PIN-ul vechi este incorect.");
        }
    }

    public abstract void afiseazaTipCard();
}