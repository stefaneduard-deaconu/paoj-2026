package com.pao.project.model;

import java.util.UUID;

public abstract class Persoana {

    protected int id;
    protected String nume;

    public Persoana(int id, String nume) {
        this.id = id;
        this.nume = nume;
    }
//
//    public Persoana(String nume){
////        this.nume = nume;
////        this.id = UUID.randomUUID();
//    }



    public int getId() {
        return id;
    }

    public abstract String getRol();
}