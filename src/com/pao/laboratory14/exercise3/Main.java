package com.pao.laboratory14.exercise3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Main {

    public record Eveniment(String nume, String startStr, String endStr) {

        public int getStartMin() {
            return convertesteInMinute(startStr);
        }

        public int getEndMin() {
            return convertesteInMinute(endStr);
        }

        private int convertesteInMinute(String timp) {
            String[] parti = timp.split(":");
            int ore = Integer.parseInt(parti[0]);
            int minute = Integer.parseInt(parti[1]);
            return ore * 60 + minute;
        }

        @Override
        public String toString() {
            return String.format("%-18s (%s - %s)", nume, startStr, endStr);
        }
    }

    public static void main(String[] args) {
        List<Eveniment> evenimente = new ArrayList<>();
        evenimente.add(new Eveniment("Concert Rock", "18:00", "20:00"));
        evenimente.add(new Eveniment("Conferinta PAO", "10:00", "12:30"));
        evenimente.add(new Eveniment("Standup Comedy", "19:30", "21:00"));
        evenimente.add(new Eveniment("Workshop IA", "11:15", "14:00"));
        evenimente.add(new Eveniment("Expozitie Arta", "09:00", "17:00"));
        evenimente.add(new Eveniment("Vizionare Film", "14:30", "16:30"));
        evenimente.add(new Eveniment("Gala Premii", "19:00", "22:30"));
        evenimente.add(new Eveniment("Sedinta Tech", "12:00", "13:30"));

        System.out.println("=== Evenimente Initiale ===");
        evenimente.forEach(System.out::println);
        System.out.println();

        evenimente.sort(Comparator.comparingInt(Eveniment::getStartMin));

        System.out.println("=== Evenimente Sortate dupa Ora de Start ===");
        evenimente.forEach(System.out::println);
        System.out.println();

        rulareVarianta1Greedy(evenimente);
        rulareVarianta2PriorityQueue(evenimente);
    }

    private static void rulareVarianta1Greedy(List<Eveniment> evenimente) {
        System.out.println("=== Varianta 1: Greedy Simplu O(N^2) ===");
        List<Integer> salileOcupate = new ArrayList<>();

        for (Eveniment ev : evenimente) {
            boolean salaGasita = false;
            int numarSala = -1;

            for (int i = 0; i < salileOcupate.size(); i++) {
                if (salileOcupate.get(i) <= ev.getStartMin()) {
                    salileOcupate.set(i, ev.getEndMin());
                    numarSala = i + 1;
                    salaGasita = true;
                    break;
                }
            }

            if (!salaGasita) {
                salileOcupate.add(ev.getEndMin());
                numarSala = salileOcupate.size();
            }

            System.out.printf("%s  →  Sala #%d%n", ev, numarSala);
        }

        System.out.println("Numar minim de sali (Varianta 1): " + salileOcupate.size());
        System.out.println("-------------------------------------------------------------\n");
    }

    private static void rulareVarianta2PriorityQueue(List<Eveniment> evenimente) {
        System.out.println("=== Varianta 2: PriorityQueue O(N log N) ===");

        if (evenimente.isEmpty()) {
            System.out.println("Numar minim de sali (Varianta 2): 0");
            return;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(evenimente.get(0).getEndMin());

        for (int i = 1; i < evenimente.size(); i++) {
            Eveniment ev = evenimente.get(i);

            if (pq.peek() <= ev.getStartMin()) {
                pq.poll();
            }

            pq.offer(ev.getEndMin());
        }

        System.out.println("Numar minim de sali (Varianta 2): " + pq.size());
        System.out.println("-------------------------------------------------------------");
    }
}