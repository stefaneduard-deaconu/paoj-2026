package com.pao.project.platforma_elearning.model;

import java.time.LocalDate;

public final class Certificat {
    private final String codUnic;
    private final String numeCursant;
    private final String numeCurs;
    private final LocalDate dataEmiterii;

    public Certificat(String codUnic, String numeCursant, String numeCurs) {
        this.codUnic = codUnic;
        this.numeCursant = numeCursant;
        this.numeCurs = numeCurs;
        this.dataEmiterii = LocalDate.now();
    }

    @Override
    public String toString() {
        return "Certificat: cod = " + codUnic + ", cursant = " + numeCursant + ", curs = " + numeCurs + ", data emiterii = " + dataEmiterii;
    }
}