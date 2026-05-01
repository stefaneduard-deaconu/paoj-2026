package com.pao.project.platforma_elearning.model;

import java.util.Objects;

public abstract class Utilizator {
    private static int idCounter = 1;
    protected int id;
    protected String nume, email, parola;

    public Utilizator(String nume, String email, String parola) {
        this.id = idCounter++;
        this.nume = nume;
        this.email = email;
        this.parola = parola;
    }

    public int getId() { return id; }
    public String getNume() { return nume; }
    public String getEmail() { return email; }
    public String getParola() { return parola; }

    public abstract String getTipUtilizator();

    @Override
    public boolean equals(Object ob) {
        if (this == ob) {
            return true;
        }

        if (!(ob instanceof Utilizator)) {
            return false;
        }

        Utilizator u = (Utilizator) ob;

        return Objects.equals(email, u.email);
    }

    @Override
    public int hashCode() { return Objects.hash(email); }

    @Override
    public String toString() {
        return "id = " + id + ", nume = " + nume + ", email = " + email;
    }
}