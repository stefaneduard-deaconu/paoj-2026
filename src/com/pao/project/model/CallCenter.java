package com.pao.project.model;

public class CallCenter extends Angajat{

    public CallCenter(int id, String nume) {
        super(id, nume);
    }

    @Override
    public String getRol() {
        return "OPERATOR";
    }
}
