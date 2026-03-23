package com.pao.laboratory05.biblioteca;

public class Carte implements Comparable<Carte> {
    private String titlu;
    private String autor;
    private int an;
    private double rating;

    public Carte(String titlu, String autor, int an, double rating) {
        this.titlu = titlu;
        this.autor = autor;
        this.an = an;
        this.rating = rating;
    }

    public String getTitlu() { return titlu; }
    public String getAutor() { return autor; }
    public int getAn() { return an; }
    public double getRating() { return rating; }

    @Override
    public String toString() {
        return "Carte{titlu='" + titlu + "', autor='" + autor + "', an=" + an + ", rating=" + rating + "}";
    }

    @Override
    public int compareTo(Carte o) {
        return Double.compare(o.rating, this.rating);
    }
}