package com.pao.project.catalog;

import com.pao.project.catalog.model.*;
import com.pao.project.catalog.service.*;
import com.pao.project.catalog.repository.*;
import com.pao.project.catalog.exception.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentService studentService = StudentService.getInstance();
        CatalogService catalogService = CatalogService.getInstance();

        MaterieRepository materieRepository = new MaterieRepository();
        ProfesorRepository profesorRepository = new ProfesorRepository();
        CatalogRepository catalogRepository = new CatalogRepository();

        Scanner scanner = new Scanner(System.in);
        boolean ruleaza = true;

        System.out.println("Conexiune la baza de date stabilita! Bun venit in Catalogul Scolar.");

        while (ruleaza) {
            System.out.println("\n========== MENIU PRINCIPAL ==========");
            System.out.println("--- ETAPA 1: Cele 10 Actiuni ---");
            System.out.println("1. Adauga un nou student");
            System.out.println("2. Adauga un profesor nou");
            System.out.println("3. Creeaza o materie noua");
            System.out.println("4. Inregistreaza o nota pentru un student");
            System.out.println("5. Cauta un student dupa ID");
            System.out.println("6. Listeaza studentii (indexare in Map)");
            System.out.println("7. Afiseaza toate notele unui student");
            System.out.println("8. Calculeaza media generala a unui student");
            System.out.println("9. Sorteaza studentii alfabetic");
            System.out.println("10. Sterge un student (simplu)");
            System.out.println("--- ETAPA 2: Tranzactii si JOIN-uri ---");
            System.out.println("11. Afiseaza catalogul complet (JOIN)");
            System.out.println("12. Afiseaza top 3 studenti dupa medie (JOIN)");
            System.out.println("13. Detalii note student (JOIN)");
            System.out.println("14. Sterge student si notele sale (TRANZACTIE JDBC)");
            System.out.println("0. Iesire din aplicatie");
            System.out.print("\nAlege o optiune: ");

            int optiune = -1;
            try {
                optiune = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Te rog introdu un numar valid!");
                continue;
            }

            switch (optiune) {
                case 1:
                    System.out.print("Nume student: ");
                    String numeSt = scanner.nextLine();
                    System.out.print("Email student: ");
                    String emailSt = scanner.nextLine();
                    System.out.print("ID Student (ex. ST01): ");
                    String idSt = scanner.nextLine();
                    studentService.adaugaStudent(new Student(numeSt, emailSt, new IdentificatorScolar(idSt)));
                    System.out.println("[Sistem] Student adaugat cu succes in BD!");
                    break;

                case 2:
                    System.out.print("Nume profesor: ");
                    String numeProf = scanner.nextLine();
                    System.out.print("Email profesor: ");
                    String emailProf = scanner.nextLine();
                    System.out.print("Salariu: ");
                    double salariu = Double.parseDouble(scanner.nextLine());
                    System.out.print("Specializare: ");
                    String specializare = scanner.nextLine();
                    profesorRepository.save(new Profesor(numeProf, emailProf, salariu, specializare));
                    System.out.println("[Sistem] Profesor adaugat cu succes in BD!");
                    break;

                case 3:
                    System.out.print("Nume materie: ");
                    String numeMat = scanner.nextLine();
                    System.out.print("Numar credite: ");
                    int credite = Integer.parseInt(scanner.nextLine());
                    materieRepository.save(new Materie(numeMat, credite));
                    System.out.println("[Sistem] Materie adaugata cu succes in BD!");
                    break;

                case 4:
                    System.out.print("ID Student (ex. ST01): ");
                    String idStNota = scanner.nextLine();
                    System.out.print("ID Materie (numar intreg, ex. 1): ");
                    int idMatNota = Integer.parseInt(scanner.nextLine());
                    System.out.print("Valoare nota (1-10): ");
                    int valNota = Integer.parseInt(scanner.nextLine());
                    System.out.print("Data acordarii (ex. 20-04-2024): ");
                    String dataNota = scanner.nextLine();
                    try {
                        catalogService.adaugaNota(idStNota, idMatNota, new Nota(valNota, dataNota));
                        System.out.println("[Sistem] Nota inregistrata cu succes in BD!");
                    } catch (NotaInvalidaException e) {
                        System.out.println("Eroare la adaugare nota: " + e.getMessage());
                    }
                    break;

                case 5:
                    System.out.print("Introdu ID-ul studentului de cautat: ");
                    String idCautat = scanner.nextLine();
                    try {
                        Student gasit = studentService.cautaDupaId(idCautat);
                        System.out.println("Student gasit: " + gasit);
                    } catch (StudentNegasitException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 6:
                    Map<String, Student> indexStudenti = studentService.getStudentiIndexati();
                    System.out.println("Studenti indexati in Map:");
                    for (Map.Entry<String, Student> entry : indexStudenti.entrySet()) {
                        System.out.println(" - Cheie ID: " + entry.getKey() + " | Valoare: " + entry.getValue());
                    }
                    break;

                case 7:
                    System.out.print("Introdu ID-ul studentului: ");
                    String idPentruNote = scanner.nextLine();
                    List<Nota> note = catalogService.getNoteStudent(idPentruNote);
                    if (note.isEmpty()) {
                        System.out.println("Studentul nu are note inregistrate.");
                    } else {
                        System.out.println("Notele studentului:");
                        for (Nota n : note) {
                            System.out.println(" - Nota: " + n.getValoare() + " (Data: " + n.getData() + ")");
                        }
                    }
                    break;

                case 8:
                    System.out.print("Introdu ID-ul studentului: ");
                    String idMedie = scanner.nextLine();
                    double medie = catalogService.calculeazaMedia(idMedie);
                    System.out.printf("Media generala a studentului este: %.2f%n", medie);
                    break;

                case 9:
                    System.out.println("Studenti sortati alfabetic (din BD):");
                    List<Student> sortati = studentService.getStudentiSortati();
                    for (Student s : sortati) {
                        System.out.println(" - " + s.getNume() + " (ID: " + s.getId() + ")");
                    }
                    break;

                case 10:
                    System.out.print("ID-ul studentului pe care vrei sa il stergi: ");
                    String idDeSters = scanner.nextLine();
                    studentService.stergeStudent(idDeSters);
                    System.out.println("[Sistem] Comanda de stergere a fost trimisa catre BD.");
                    break;

                case 11:
                    catalogRepository.afiseazaCatalogComplet();
                    break;

                case 12:
                    catalogRepository.afiseazaTopStudentiDupaMedie();
                    break;

                case 13:
                    System.out.print("ID-ul studentului pentru care doresti detaliile avansate: ");
                    String idDetalii = scanner.nextLine();
                    catalogRepository.detaliiNoteStudent(idDetalii);
                    break;

                case 14:
                    System.out.print("ID-ul studentului de sters (Executie Tranzactie): ");
                    String idTranzactie = scanner.nextLine();
                    try {
                        catalogRepository.stergeStudentSiNote(idTranzactie);
                        System.out.println("[Sistem] Tranzactie incheiata cu succes! Studentul si notele au fost sterse simultan.");
                    } catch (SQLException e) {
                        System.out.println("[Sistem] Eroare la tranzactie (S-a efectuat ROLLBACK): " + e.getMessage());
                    }
                    break;

                case 0:
                    ruleaza = false;
                    System.out.println("[Sistem] Se inchide aplicatia. La revedere!");
                    break;

                default:
                    System.out.println("Optiune invalida. Te rog sa alegi un numar din meniu.");
            }
        }

        scanner.close();
    }
}