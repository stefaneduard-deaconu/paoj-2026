package com.pao.laboratory07.exercise1;

import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Stack<StareComanda> istoric = new Stack<>();

        if (!scanner.hasNext()) return;
        StareComanda stareCurenta = StareComanda.valueOf(scanner.next());
        System.out.println(stareCurenta);

        boolean msjQuit = false;

        while (scanner.hasNext()) {
            String comanda = scanner.next();

            if (comanda.equalsIgnoreCase("QUIT")) {
                if (msjQuit) {
                    System.out.println("Comanda este in stare finala.");
                }
                break;
            }

            if (StareComanda.esteFinala(stareCurenta) && !comanda.equalsIgnoreCase("undo")) {
                System.out.println("Comanda este in stare finala.");
                msjQuit = false;
                continue;
            }

            switch (comanda.toLowerCase()) {
                case "next":
                    istoric.push(stareCurenta);
                    stareCurenta = StareComanda.tranzitieNext(stareCurenta);
                    System.out.println(stareCurenta);

                    msjQuit = StareComanda.esteFinala(stareCurenta);
                    break;

                case "cancel":
                    istoric.push(stareCurenta);
                    stareCurenta = StareComanda.ANULATA;
                    System.out.println(stareCurenta);

                    msjQuit = true;
                    break;

                case "undo":
                    if (!istoric.isEmpty()) {
                        stareCurenta = istoric.pop();
                    }
                    System.out.println(stareCurenta);

                    msjQuit = false;
                    break;
            }
        }
        scanner.close();
    }
}