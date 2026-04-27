package com.pao.project.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Comanda {

    final private String ID;
    private Client client;
    private Restaurant restaurant;
    private Livrator sofer;
    private List<Produs> produse = new ArrayList<>();
    private String status;

    public Comanda(){
        this.ID = UUID.randomUUID().toString();
        this.status = "CREATA";

    }

    public Comanda(String id, Client client, Restaurant restaurant) {
        this.ID = id;
        this.client = client;
        this.restaurant = restaurant;
        this.status = "CREATA";

    }

    public String getId(){
        return this.ID;
    }

    public void setSofer(Livrator sofer) {
        this.sofer = sofer;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void adaugaProdus(Produs produs) {
        produse.add(produs);
    }


    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Livrator getSofer() {
        return sofer;
    }

    public List<Produs> getProduse() {
        return produse;
    }

    public void setProduse(List<Produs> produse) {
        this.produse = produse;
    }

    public String getStatus() {
        return status;
    }
}
