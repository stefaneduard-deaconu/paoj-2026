package com.pao.project.platforma_elearning.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Curs implements Comparable<Curs> {
    private static int cursCounter = 1;
    private int id;
    private String titlu, categorie;
    private double pret;
    private int idInstructor;

    private List<Lectie> lectii;
    private List<Quiz> quizuri;
    private List<Recenzie> recenzii;

    public Curs(String titlu, String categorie, double pret, int idInstructor) {
        this.id = cursCounter++;
        this.titlu = titlu;
        this.categorie = categorie;
        this.pret = pret;
        this.idInstructor = idInstructor;
        this.lectii = new ArrayList<>();
        this.quizuri = new ArrayList<>();
        this.recenzii = new ArrayList<>();
    }

    public List<Lectie> getLectii() { return lectii; }
    public List<Quiz> getQuizuri() { return quizuri; }
    public int getId() { return id; }
    public String getTitlu() { return titlu; }
    public double getPret() { return pret; }
    public int getIdInstructor() {
        return idInstructor;
    }
    public void setPret(double pret) {
        this.pret = pret;
    }
    public String getCategorie() { return categorie; }

    public List<Recenzie> getRecenzii() { return recenzii; }

    public void adaugaLectie(Lectie l) { this.lectii.add(l); }
    public void adaugaQuiz(Quiz q) { this.quizuri.add(q); }
    public void adaugaRecenzie(Recenzie r) { this.recenzii.add(r); }

    @Override
    public boolean equals(Object ob) {
        if (this == ob) {
            return true;
        }

        if (!(ob instanceof Curs)) {
            return false;
        }

        Curs curs = (Curs) ob;

        return id == curs.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Curs: id = " + id + ", titlu = " + titlu + ", categorie = " + categorie + ", pret = " + pret;
    }

    @Override
    public int compareTo(Curs altCurs) {
        return Double.compare(this.pret, altCurs.pret);
    }

    public void stergeRecenzieCursant(int idCursant) {
        recenzii.removeIf(r -> r.getIdCursant() == idCursant);
    }

    public List<Integer> extrageIduriQuiz() {
        List<Integer> iduri = new ArrayList<>();

        for (Quiz q : this.quizuri) {
            iduri.add(q.getId());
        }

        return iduri;
    }
}