package com.pao.laboratory01.comparators;

import java.util.Arrays;
import java.util.Comparator;

public class Podcast implements Comparable<Podcast> {
    private String title;
    private int durationInSeconds;

    public Podcast(String title, int durationInSeconds) {
        this.title = title;
        this.durationInSeconds = durationInSeconds;
    }

    public String getTitle() {
        return title;
    }

    public int getDurationInSeconds() {
        return durationInSeconds;
    }

    @Override
    public String toString() {
        return "Podcast{" +
                "title='" + title + '\'' +
                ", durationInSeconds=" + durationInSeconds +
                '}';
    }

    @Override
    public int compareTo(Podcast o) {
        return this.title.compareTo(o.title);
    }

    public static void main(String[] args) {
        Podcast[] podcasts = {
                new Podcast("Tech Talk", 2400),
                new Podcast("Arta Conversatiei", 3600),
                new Podcast("Mindset", 1800)
        };

        Arrays.sort(podcasts);
        System.out.println("Sortate dupa titlu:");
        System.out.println(Arrays.toString(podcasts));

        Arrays.sort(podcasts, new PodcastLengthComparator());
        System.out.println("Sortate dupa durata (crescator):");
        System.out.println(Arrays.toString(podcasts));

        Arrays.sort(podcasts, (p1, p2) -> p2.getDurationInSeconds() - p1.getDurationInSeconds());
        System.out.println("Sortate dupa durata (descrescator, lambda):");
        System.out.println(Arrays.toString(podcasts));
    }
}

class PodcastLengthComparator implements Comparator<Podcast> {
    @Override
    public int compare(Podcast p1, Podcast p2) {
        return p1.getDurationInSeconds() - p2.getDurationInSeconds();
    }
}