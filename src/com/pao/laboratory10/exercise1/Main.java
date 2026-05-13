package com.pao.laboratory10.exercise1;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LinkedList<Tranzactie> coada = new LinkedList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] tokens = line.split("\\s+");
            String comanda = tokens[0].toUpperCase();

            try {
                switch (comanda) {
                    case "ENQUEUE": {
                        int id = Integer.parseInt(tokens[1]);
                        double suma = Double.parseDouble(tokens[2]);
                        String data = tokens[3];
                        TipTranzactie tip = TipTranzactie.valueOf(tokens[4].toUpperCase());

                        coada.addLast(new Tranzactie(id, suma, data, tip));
                        break;
                    }

                    case "DEQUEUE": {
                        if (coada.isEmpty()) {
                            System.out.println("Coada goala.");
                        } else {
                            System.out.println("Procesat: " + coada.removeFirst());
                        }
                        break;
                    }

                    case "PUSH": {
                        int id = Integer.parseInt(tokens[1]);
                        double suma = Double.parseDouble(tokens[2]);
                        String data = tokens[3];
                        TipTranzactie tip = TipTranzactie.valueOf(tokens[4].toUpperCase());
                        coada.addFirst(new Tranzactie(id, suma, data, tip));
                        break;
                    }

                    case "POP": {
                        if (coada.isEmpty()) {
                            System.out.println("Coada goala.");
                        } else {
                            System.out.println("Extras: " + coada.removeFirst());
                        }
                        break;
                    }

                    case "REMOVE_DEBIT": {
                        int contor = 0;
                        Iterator<Tranzactie> it = coada.iterator();
                        while (it.hasNext()) {
                            if (it.next().getTip() == TipTranzactie.DEBIT) {
                                it.remove();
                                contor++;
                            }
                        }
                        System.out.println("Eliminat " + contor + " tranzactii DEBIT.");
                        break;
                    }

                    case "REMOVE_BELOW": {
                        double prag = Double.parseDouble(tokens[1]);
                        int contor = 0;
                        Iterator<Tranzactie> it = coada.iterator();
                        while (it.hasNext()) {
                            if (it.next().getSuma() < prag) {
                                it.remove();
                                contor++;
                            }
                        }
                        System.out.printf("Eliminat %d tranzactii sub %.2f RON.\n", contor, prag);
                        break;
                    }

                    case "PRINT": {
                        for (Tranzactie t : coada) {
                            System.out.println(t);
                        }
                        break;
                    }

                    case "SIZE": {
                        System.out.println("Dimensiune coada: " + coada.size());
                        break;
                    }

                    default:
                        break;
                }
            } catch (Exception e) {
                System.err.println("Eroare la procesarea comenzii: " + line);
            }
        }

        scanner.close();
    }
}