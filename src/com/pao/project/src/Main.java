package com.pao.project.src;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BankingService service = new BankingService();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("=== SISTEM BANCAR JAVA - ETAPA I ===");

        while (running) {
            System.out.println("\n--- MENIU PRINCIPAL ---");
            System.out.println("1. Adauga Client nou");
            System.out.println("2. Deschide Cont nou (Curent/Economii)");
            System.out.println("3. Depunere numerar");
            System.out.println("4. Retragere numerar");
            System.out.println("5. Transfer intre conturi");
            System.out.println("6. Afiseaza tranzactiile unui cont (Extras)");
            System.out.println("7. Afiseaza averea totala a unui client");
            System.out.println("8. Emite Card nou");
            System.out.println("9. Afiseaza toate conturile băncii (SORTATE)");
            System.out.println("10. Calculeaza dobanda acumulata (Economii)");
            System.out.println("11. Blocare/Deblocare Card");
            System.out.println("12. Caută Client după CNP");
            System.out.println("13. Generează Extras (Filtru Dată)");
            System.out.println("14. Calculează grad îndatorare (Loan)");
            System.out.println("0. Iesire");
            System.out.print("Alege o optiune: ");

            int optiune = scanner.nextInt();
            scanner.nextLine();

            switch (optiune) {
                case 1:
                    System.out.print("Nume: "); String nume = scanner.nextLine();
                    System.out.print("Prenume: "); String prenume = scanner.nextLine();
                    System.out.print("CNP: "); String cnp = scanner.nextLine();
                    service.inregistreazaClient(new Client(nume, prenume, cnp));
                    break;
                case 2:
                    System.out.print("CNP Client: "); String cnpC = scanner.nextLine();
                    System.out.print("Tip Cont (1-Curent, 2-Economii): "); int tip = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("IBAN: "); String iban = scanner.nextLine();
                    if (tip == 1) service.deschideCont(cnpC, new CurrentAccount(iban, 0, "RON", 500, 5));
                    else service.deschideCont(cnpC, new SavingsAccount(iban, 0, "RON", 3.0, 12));
                    break;
                case 3:
                    System.out.print("IBAN: "); String ibanD = scanner.nextLine();
                    System.out.print("Suma: "); double sumaD = scanner.nextDouble();
                    service.depunereInCont(ibanD, sumaD);
                    break;
                case 4:
                    System.out.print("IBAN: "); String ibanR = scanner.nextLine();
                    System.out.print("Suma: "); double sumaR = scanner.nextDouble();
                    service.efectueazaRetragere(ibanR, sumaR);
                    break;
                case 5:
                    System.out.print("IBAN Sursa: "); String s = scanner.nextLine();
                    System.out.print("IBAN Destinatar: "); String d = scanner.nextLine();
                    System.out.print("Suma: "); double sumaT = scanner.nextDouble();
                    service.transfer(s, d, sumaT);
                    break;
                case 9:
                    service.afiseazaRaportConturiSortate();
                    break;
                case 12:
                    System.out.print("Introdu CNP: "); String cnpCautat = scanner.nextLine();
                    Client gasit = service.gasesteClientDupaCnp(cnpCautat);
                    System.out.println(gasit != null ? gasit : "Clientul nu a fost găsit.");
                    break;
                case 0:
                    running = false;
                    System.out.println("Aplicatie inchisa.");
                    break;
                default:
                    System.out.println("Optiune invalida!");
            }
        }
    }
}