package com.pao.laboratory05.playlist;

/**
 * Exercise 1 — Playlist muzică
 *
 * Cerințele complete se află în:
 *   src/com/pao/laboratory05/Readme.md  →  secțiunea "Exercise 1 — Playlist"
 *
 * Creează fișierele de la zero în acest pachet, apoi rulează Main.java
 * pentru a verifica output-ul așteptat din Readme.
 */
public class Main {
    public static void main(String[] args) {
        Playlist playlist = new Playlist("Road Trip");
        playlist.addSong(new Song("Waterloo", "ABBA", 174));
        playlist.addSong(new Song("Bohemian Rhapsody", "Queen", 354));
        playlist.addSong(new Song("Imagine", "John Lennon", 187));
        playlist.addSong(new Song("Smells Like Teen Spirit", "Nirvana", 301));

        System.out.println("=== " + playlist.getName() + " ===");
        System.out.println("Durata totala: " + playlist.getTotalDuration() + "s\n");

        System.out.println("--- Sortate după titlu ---");
        playlist.printSortedByTitle();

        System.out.println("\n--- Sortate după durată ---");
        playlist.printSortedByDuration();
    }
}
