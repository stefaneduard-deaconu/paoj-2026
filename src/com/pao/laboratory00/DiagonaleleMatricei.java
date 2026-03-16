package com.pao.laboratory00;

import java.util.Scanner;

/**
 * Exercitiul 2
 *
 * Cititi de la tastatura o matrice de n ori n elemente REALE.
 *
 * 1. Afisati matricea in consola.
 * 2. Afisati suma elementelor de pe diagonala principala
 *    si produsul elementelor de pe diagonala secundara.
 *
 */

public class DiagonaleleMatricei {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Introduceti dimensiunea matricei: ");
        int n = scanner.nextInt();

        double[][] mat = new double[n][n];

        System.out.println("Introduceti elementele matricei:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                mat[i][j] = scanner.nextDouble();
            }
        }

        System.out.println("Matricea este:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(mat[i][j] + "\t");
            }
            System.out.println();
        }

        double sumaPrincipala = 0;
        double produsSecundara = 1.0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    sumaPrincipala += mat[i][j];
                }
                if (i + j == n - 1) {
                    produsSecundara *= mat[i][j];
                }
            }
        }

        System.out.println("Suma de pe diagonala principala: " + sumaPrincipala);
        System.out.println("Produsul de pe diagonala secundara: " + produsSecundara);

        scanner.close();
    }
}
