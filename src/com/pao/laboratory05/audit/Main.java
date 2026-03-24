package com.pao.laboratory05.audit;

/**
 * Exercise 4 (Bonus) — Audit Log
 *
 * Cerințele complete se află în:
 *   src/com/pao/laboratory05/Readme.md  →  secțiunea "Exercise 4 (Bonus) — Audit"
 *
 * Extinde soluția de la Exercise 3 cu un sistem de audit bazat pe record.
 * Creează fișierele de la zero în acest pachet, apoi rulează Main.java
 * pentru a verifica output-ul așteptat din Readme.
 */
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AngajatService service = AngajatService.getInstance();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Gestionare Angajati (cu Audit) =====");
            System.out.println("1. Adauga angajat");
            System.out.println("2. Listare dupa salariu");
            System.out.println("3. Cauta dupa departament");
            System.out.println("4. Afiseaza audit log");
            System.out.println("0. Iesire");
            System.out.print("Optiune: ");

            int optiune = scanner.nextInt();
            scanner.nextLine();

            if (optiune == 0) break;

            switch (optiune) {
                case 1:
                    System.out.print("Nume: ");
                    String nume = scanner.nextLine();
                    System.out.print("Dept: ");
                    String dNume = scanner.nextLine();
                    System.out.print("Locatie: ");
                    String dLoc = scanner.nextLine();
                    System.out.print("Salariu: ");
                    double sal = scanner.nextDouble();
                    service.addAngajat(new Angajat(nume, new Departament(dNume, dLoc), sal));
                    break;
                case 2:
                    service.listBySalary();
                    break;
                case 3:
                    System.out.print("Nume departament: ");
                    service.findByDepartament(scanner.nextLine());
                    break;
                case 4:
                    service.printAuditLog();
                    break;
                default:
                    System.out.println("Invalida.");
            }
        }
        scanner.close();
    }
}
