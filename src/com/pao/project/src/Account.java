package com.pao.project.src;

import java.util.ArrayList;
import java.util.List;

public abstract class Account implements Comparable<Account> {
    private static int contorId = 100;

    private final int idCont;
    protected String iban;
    protected double sold;
    private String moneda;

    private List<Transaction> tranzactii;

    public Account(String iban, double soldInitial, String moneda) {
        this.idCont = contorId++;
        this.iban = iban;
        this.sold = soldInitial;
        this.moneda = moneda;
        this.tranzactii = new ArrayList<>();
    }

    public int getIdCont() { return idCont; }

    public String getIban() { return iban; }
    public void setIban(String iban) { this.iban = iban; }

    public double getSold() { return sold; }

    public String getMoneda() { return moneda; }

    public List<Transaction> getTranzactii() {
        return new ArrayList<>(tranzactii);
    }

    public void adaugaTranzactie(Transaction t) {
        this.tranzactii.add(t);
    }

    public abstract void afiseazaDetaliiSpecific();

    public void depunere(double suma) {
        if (suma > 0) {
            this.sold += suma;
            Transaction t = new Transaction(suma, "DEPUNERE", "Depunere numerar");
            this.adaugaTranzactie(t);
            System.out.println("Succes: S-a depus " + suma + " " + getMoneda());
        } else {
            System.out.println("Eroare: Suma trebuie sa fie pozitiva.");
        }
    }

    public boolean retragere(double suma) {
        if (suma > 0 && this.sold >= suma) {
            this.sold -= suma;
            Transaction t = new Transaction(suma, "RETRAGERE", "Retragere numerar");
            this.adaugaTranzactie(t);
            System.out.println("Succes: S-a retras " + suma + " " + getMoneda());
            return true;
        } else {
            System.out.println("Eroare: Fonduri insuficiente sau suma invalida.");
            return false;
        }
    }

    @Override
    public int compareTo(Account altCont) {
        return Double.compare(this.sold, altCont.sold);
    }

    @Override
    public String toString() {
        return "Cont ID: " + idCont + " | IBAN: " + iban + " | Sold: " + sold + " " + moneda;
    }
}