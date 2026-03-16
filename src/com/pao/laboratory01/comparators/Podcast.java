package com.pao.laboratory01.comparators;

import java.util.Arrays;
import java.util.Comparator;

// EXERCITIU 1: cream in pachetul comparators o clasa Podcast cu durata (secunde, int) si titlu (string)
// dupa modelul AudioBook.java si Book.java, implementati:
// 1. toString — pentru afisare frumoasa
// 2. Comparable<Podcast> cu compareTo — sortare dupa titlu
// 3. un Comparator extern (PodcastLengthComparator) — sortare dupa durata
// 4. o metoda main in care cream cateva podcast-uri si le sortam in ambele moduri

public class Podcast implements Comparable<Podcast> {

    // TODO: adauga atributele: title (String), durationInSeconds (int)
    // TODO: adauga constructor cu ambele atribute
    // TODO: suprascrie toString()
    // TODO: implementeaza Comparable<Podcast> si suprascrie compareTo (dupa titlu)
    // TODO: adauga getter pentru durationInSeconds (necesar pentru comparator)

    // Metoda main — codul final care trebuie sa functioneze dupa implementare.
    // Ruleaza-l ca sa verifici ca totul e corect!
    private String title;
    private int durationInSeconds;

    public Podcast(String title, int durationInSeconds) {
        this.title = title;
        this.durationInSeconds = durationInSeconds;
    }

    public String getTitle() { return title; }
    public int getDurationInSeconds() { return durationInSeconds; }

    @Override
    public String toString() {
        return "Podcast{" + "title='" + title + '\'' +
                ", durationInSeconds=" + durationInSeconds + '}';
    }

    @Override
    public int compareTo(Podcast o) {
        // Sortare naturala dupa titlu
        return this.title.compareTo(o.title);
    }

    public static void main(String[] args) {
        Podcast[] podcasts = {
                new Podcast("Tech Talk", 2400),
                new Podcast("Arta Conversatiei", 3600),
                new Podcast("Mindset", 1800)
        };

        // 1. Sortare dupa titlu (naturala)
        Arrays.sort(podcasts);
        System.out.println("Sortate dupa titlu: " + Arrays.toString(podcasts));

        // 2. Sortare dupa durata crescator (Comparator extern)
        Arrays.sort(podcasts, new PodcastLengthComparator());
        System.out.println("Sortate dupa durata: " + Arrays.toString(podcasts));

        // 3. Sortare dupa durata descrescator (Lambda)
        Arrays.sort(podcasts, (p1, p2) -> p2.getDurationInSeconds() - p1.getDurationInSeconds());
        System.out.println("Sortate descrescator: " + Arrays.toString(podcasts));
    }
}

// TODO: creeaza o clasa PodcastLengthComparator care implementeaza Comparator<Podcast>
//  si compara dupa durationInSeconds (vezi AudioBookLengthComparator ca model)
class PodcastLengthComparator implements Comparator<Podcast> {
    @Override
    public int compare(Podcast p1, Podcast p2) {
        return p1.getDurationInSeconds() - p2.getDurationInSeconds();
    }
}