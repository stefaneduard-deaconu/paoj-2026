package com.pao.laboratory00;

import java.util.Scanner;

/**
 * Exercitiul 1
 *
 * Cititi de la tastatura un sir cu n elemente intregi.
 *
 * 1. Afisati elementele sirului in doua modalitati.
 * 2. Afisati media aritmetica a elementelor sirului.
 *
 */

public class MediaAritmetica {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Introduceti numarul de elemente: ");
        int n = scanner.nextInt();

        int[] sir = new int[n];
        int suma = 0;

        System.out.println("Introduceti cele " + n + " elemente:");
        for (int i = 0; i < n; i++) {
            sir[i] = scanner.nextInt();
            suma += sir[i];
        }

        System.out.print("Afisare cu index: ");
        for (int i = 0; i < n; i++) {
            System.out.print(sir[i] + " ");
        }

        System.out.print("\nAfisare cu enhanced for: ");
        for (int element : sir) {
            System.out.print(element + " ");
        }

        double media = (double) suma / n;
        System.out.println("\nMedia aritmetica este: " + media);

        scanner.close();
    }
}
