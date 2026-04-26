package com.pao.project.catalog.model;

import java.util.Objects;

public abstract class Persoana {
    protected String nume;
    protected String email;

    public Persoana(String nume, String email) {
        this.nume = nume;
        this.email = email;
    }

    public abstract String getRol(); // metoda abstracta

    public String getNume() { return nume; }
    public void setNume(String nume) { this.nume = nume; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persoana persoana = (Persoana) o;
        return Objects.equals(nume, persoana.nume) && Objects.equals(email, persoana.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nume, email);
    }
}