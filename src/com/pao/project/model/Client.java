package com.pao.project.model;

public class Client extends Persoana {




    public Client(int id, String nume) {
        super(id, nume);
    }

    @Override
    public String getRol() {
        return "CLIENT";
    }



}