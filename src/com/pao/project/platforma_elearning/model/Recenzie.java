package com.pao.project.platforma_elearning.model;

public class Recenzie {
    private int idCursant;
    private int rating;
    private String comentariu;

    public Recenzie(int idCursant, int rating, String comentariu) {
        this.idCursant = idCursant;
        this.rating = rating;
        this.comentariu = comentariu;
    }

    public int getIdCursant() {
        return idCursant;
    }

    @Override
    public String toString() {
        return "Recenzie: rating = " + rating + "/5" + ", comentariu = " + comentariu;
    }
}