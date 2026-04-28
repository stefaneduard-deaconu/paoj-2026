package com.pao.project.model;

import java.util.*;

public class Restaurant {

    private int CUI;
    private int id;
    private String nume;
    private Locatie locatie;
    private List<Produs> meniu = new ArrayList<>();
    private int nrVanzari;


    public Restaurant(int cui, int id, String nume, Locatie locatie) {
        this.CUI = cui;
        this.id = id;
        this.nume = nume;
        this.locatie = locatie;
    }

    public void adaugaProdus(Produs produs) {
        meniu.add(produs);
    }

    public List<Produs> getMeniu() {
        return meniu;
    }

    public int getId() {
        return id;
    }

    public String getNume() {
        return nume;
    }

    public Locatie getLocatie() {
        return locatie;
    }

    public int getNrVanzari() {
        return this.nrVanzari;
    }

    public void incrementNrVanzari() {
        this.nrVanzari++;
    }

    public int getCUI() {
        return CUI;
    }

    public void setCUI(int CUI) {
        this.CUI = CUI;
    }

    public void setMeniu() {
        Scanner scanner = new Scanner(System.in);

        this.meniu = new ArrayList<>();

        System.out.println("Setare meniu:");

        System.out.print("Numarul de produse al meniului:");
        int nrProduse = scanner.nextInt();

        if(nrProduse < 0){
            throw new RuntimeException("Nr-ul de produse trebuie se fie mai mare ca 0");
        }

        for(int i = 0; i < nrProduse; i++){

            System.out.print("Id-ul produsului:");
            int idProdus = scanner.nextInt();

            System.out.print("Pretul produsului:");
            double pretProdus = scanner.nextDouble();

            System.out.print("Numele produsului:");
            String numeProdus = scanner.next();

            Produs produs = new Produs(idProdus, numeProdus, pretProdus);

            this.meniu.add(produs);
        }


    }
}