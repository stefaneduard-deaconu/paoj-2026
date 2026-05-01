package com.pao.project.platforma_elearning.service;

import com.pao.project.platforma_elearning.model.*;
import com.pao.project.platforma_elearning.exception.*;

import java.util.List;
import java.util.Scanner;

public class MeniuService {
    private static MeniuService instance;
    private final UtilizatorService utilizatorService = UtilizatorService.getInstance();
    private final CursService cursService = CursService.getInstance();
    private final EvaluareService evaluareService = EvaluareService.getInstance();
    private final Scanner scanner = new Scanner(System.in);

    private Utilizator utilizatorLogat = null;

    private MeniuService() {
        try {
            utilizatorService.inregistrare(new Admin("Sef sistem", "admin@test.com", "1234", 10000, 10));
        }

        catch (Exception ignored) {}
    }

    public static MeniuService getInstance() {
        if (instance == null) {
            instance = new MeniuService();
        }

        return instance;
    }

    public void porneste() {
        while (true) {
            if (utilizatorLogat == null) {
                afiseazaMeniuStart();
            }

            else {
                afiseazaMeniuPrincipal();
            }
        }
    }

    private void afiseazaMeniuStart() {
        System.out.println("\nPlatforma e-learning");
        System.out.println("1. Login\n2. Inregistrare cursant\n3. Inregistrare instructor\n4. Rulare exemplu\n0. Inchide aplicatia");
        System.out.print("Selectie: ");

        int opt = citesteIntreg();
        switch (opt) {
            case 1 -> executaLogin();
            case 2 -> executaInregistrare(true);
            case 3 -> executaInregistrare(false);
            case 4 -> ruleazaScenariuDemonstrativ();
            case 0 -> System.exit(0);
            default -> System.out.println("Optiune invalida");
        }
    }

    private void afiseazaMeniuPrincipal() {
        System.out.println("\nBine ai venit, " + utilizatorLogat.getNume().toUpperCase() + " (" + utilizatorLogat.getTipUtilizator() + ")");

        if (utilizatorLogat instanceof Admin) {
            afiseazaOptiuniAdmin();
        }

        else if (utilizatorLogat instanceof Instructor) {
            afiseazaOptiuniInstructor();
        }

        else if (utilizatorLogat instanceof Cursant) {
            afiseazaOptiuniCursant();
        }

        System.out.println("0. Logout");
        System.out.print("Selectie: ");
        gestioneazaOptiune(citesteIntreg());
    }

    private void executaLogin() {
        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Parola: ");
        String parola = scanner.nextLine();

        try {
            utilizatorLogat = utilizatorService.login(email, parola);
            System.out.println("Autentificare reusita!");
        }

        catch (UtilizatorNegasitException e) {
            System.out.println("Eroare: " + e.getMessage());
        }
    }

    private void executaInregistrare(boolean esteCursant) {
        System.out.print("Nume complet: ");
        String nume = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Parola: ");
        String parola = scanner.nextLine();

        try {
            if (esteCursant) {
                utilizatorService.inregistrare(new Cursant(nume, email, parola, 0));
                System.out.println("Cont de cursant creat. Va puteti loga");
            }

            else {
                System.out.print("Specializare: "); String spec = scanner.nextLine();
                utilizatorService.inregistrare(new Instructor(nume, email, parola, 3000, spec));
                System.out.println("Cont de instructor creat. Va puteti loga");
            }

        }

        catch (EntitateExistentaException e) {
            System.out.println("Eroare: " + e.getMessage());
        }
    }

    public void ruleazaScenariuDemonstrativ() {
        System.out.println("\nScenariu exemplu");

        try {
            System.out.println("\nActiunea 1 & 2: Inregistrare utilizatori");
            Instructor instructor = new Instructor("Andrei Popescu", "andrei@curs.ro", "pass1", 5000, "Java Development");
            Cursant cursant = new Cursant("Ionut Alexandrescu", "ionut@stud.ro", "pass2", 0);
            utilizatorService.inregistrare(instructor);
            utilizatorService.inregistrare(cursant);

            System.out.println("\nActiunea 3: Login");
            this.utilizatorLogat = utilizatorService.login("ionut@stud.ro", "pass2");
            System.out.println("Login reusit pentru: " + utilizatorLogat.getNume());

            System.out.println("\nActiunea 4: Alimentare portofel");
            utilizatorService.alimenteazaPortofel(cursant.getEmail(), 200.0);

            System.out.println("\nActiunea 5: Adaugare curs");
            Curs cursJava = new Curs("Programare Java Pro", "IT", 150.0, instructor.getId());
            cursService.adaugaCurs(cursJava);
            System.out.println("Curs creat cu succes!");

            System.out.println("\nActiunea 11: Cautare curs (dupa categorie)");
            cursService.afiseazaCursuriDupaCategorie("IT");

            System.out.println("\nActiunea 6: Inrolare cursant");
            executaInrolareDirecta(cursant, cursJava);

            System.out.println("\nActiunea 7: Adaugare lectie");
            cursJava.adaugaLectie(new Lectie("Introducere in OOP", 45, "Clase si obiecte"));
            System.out.println("Lectie adaugata");

            System.out.println("\nActiunea 8: Creare quiz");
            Quiz q1 = new Quiz(101, "Test Initial", 5, 5.0);
            cursJava.adaugaQuiz(q1);
            System.out.println("Quiz adaugat");

            System.out.println("\nActiunea 9: Sustinere quiz");
            evaluareService.salveazaScorQuiz(cursant.getId(), q1.getId(), 8.5);

            System.out.println("\nActiunea 13: Afisare clasament");
            afiseazaClasamentQuiz();

            System.out.println("\nActiunea 14: Modificare pret curs");
            cursJava.setPret(120.0);
            System.out.println("Pret nou: " + cursJava.getPret());
            System.out.println("Pret actualizat");

            System.out.println("\nSectiune Studiu: Parcurgere lectie");
            Inrolare inr = evaluareService.cautaInrolare(1);

            if (inr != null) {
                System.out.println("Lectii disponibile in curs:");
                cursJava.getLectii().forEach(l -> System.out.println("  - " + l.getTitlu() + " (" + l.getDurataMinute() + " min)"));

                System.out.println("\nQuiz-uri disponibile in curs:");

                if (cursJava.getQuizuri().isEmpty()) {
                    System.out.println("Niciun quiz disponibil inca");
                }

                else {
                    cursJava.getQuizuri().forEach(q -> System.out.println("  - ID: " + q.getId() + " | Titlu: " + q.getTitlu()));
                }

                System.out.println("");

                evaluareService.inregistreazaParcurgereLectie(inr, cursJava.getLectii().size());

                System.out.println("Ai ales sa parcurgi o lectie. Progresul tau a crescut la " + String.format("%.1f", inr.getProgres()) + "%");

                System.out.println("\nActiunea 10: Generare certificat");
                evaluareService.genereazaCertificat(cursant.getNume(), cursJava.getTitlu(), inr.getProgres());
            }

            System.out.println("\nActiunea 12: Adaugare recenzie");
            cursJava.adaugaRecenzie(new Recenzie(cursant.getId(), 5, "Excelent!"));
            System.out.println("Recenzie salvata in sistem si vizibila pentru instructor");

            System.out.println("\nActiunea 15: Stergere curs");
            List<Integer> iduriQuiz = cursJava.extrageIduriQuiz();
            cursService.stergeCurs(cursJava.getTitlu());
            evaluareService.stergeDateAsociateCursului(cursJava.getId(), iduriQuiz);

            System.out.println("\n");
            this.utilizatorLogat = null;

        }

        catch (Exception e) {
            System.out.println("Eroare in demo: " + e.getMessage());
        }
    }

    private void executaInrolareDirecta(Cursant c, Curs curs) throws Exception {
        if (c.getPortofelVirtual() >= curs.getPret()) {
            c.setPortofelVirtual(c.getPortofelVirtual() - curs.getPret());
            evaluareService.adaugaInrolare(new Inrolare(c.getId(), curs.getId()));
            System.out.println("Inrolare reusita pentru: " + curs.getTitlu());
        }
    }

    private void afiseazaOptiuniAdmin() {
        System.out.println("1. Afiseaza toti utilizatorii\n2. Sterge un curs din platforma\n3. Afiseaza clasament quiz");
    }

    private void afiseazaOptiuniInstructor() {
        System.out.println("1. Creeaza curs nou\n2. Adauga lectie la curs\n3. Adauga quiz la curs");
        System.out.println("4. Modifica pret curs\n5. Vezi cursurile mele\n6. Vezi recenziile cursurilor mele\n7. Sterge un curs propriu\n8. Afiseaza clasamentul global la quiz-uri");
    }

    private void afiseazaOptiuniCursant() {
        System.out.println("1. Alimenteaza portofel\n2. Catalog cursuri\n3. Inroleaza-te la un curs");
        System.out.println("4. Sectiunea de studiu\n5. Vezi istoric si progres inrolari\n6. Cauta curs dupa categorie\n7. Renunta la un curs");
    }

    private void gestioneazaOptiune(int opt) {
        if (opt == 0) {
            utilizatorLogat = null;
            System.out.println("Delogare cu succes");
            return;
        }

        try {
            if (utilizatorLogat instanceof Admin) {
                switch (opt) {
                    case 1 -> utilizatorService.afiseazaTotiUtilizatorii();
                    case 2 -> {
                        System.out.print("Titlu curs de sters: ");
                        cursService.stergeCurs(scanner.nextLine());
                    }
                    case 3 -> afiseazaClasamentQuiz();
                }
            }

            else if (utilizatorLogat instanceof Instructor) {
                switch (opt) {
                    case 1 -> executaAdaugareCurs();
                    case 2 -> executaAdaugareLectie();
                    case 3 -> executaAdaugareQuiz();
                    case 4 -> executaModificarePret();
                    case 5 -> cursService.afiseazaCursuriInstructor(utilizatorLogat.getId());
                    case 6 -> executaVizualizareRecenziiInstructor();
                    case 7 -> executaStergereCursPropriu();
                    case 8 -> afiseazaClasamentQuiz();
                }
            }

            else if (utilizatorLogat instanceof Cursant) {
                switch (opt) {
                    case 1 -> executaAlimentarePortofel();
                    case 2 -> cursService.afiseazaCursuriDupaPret();
                    case 3 -> executaInrolare();
                    case 4 -> executaSesiuneStudiu();
                    case 5 -> evaluareService.afiseazaInrolariUtilizator(utilizatorLogat.getId());
                    case 6 -> {
                        System.out.print("Introdu categoria cautata: ");
                        cursService.afiseazaCursuriDupaCategorie(scanner.nextLine());
                    }
                    case 7 -> executaStergereInrolare();
                }
            }
        }

        catch (Exception e) { System.out.println("Eroare: " + e.getMessage()); }
    }

    private void executaAdaugareCurs() {
        System.out.print("Titlu curs: ");
        String titlu = scanner.nextLine();

        System.out.print("Categorie: ");
        String cat = scanner.nextLine();

        System.out.print("Pret: ");
        double pret = Double.parseDouble(scanner.nextLine());

        try {
            cursService.adaugaCurs(new Curs(titlu, cat, pret, utilizatorLogat.getId()));
            System.out.println("Curs creat cu succes!");
        }

        catch (EntitateExistentaException e) {
            System.out.println("Eroare: " + e.getMessage());
        }
    }

    private void executaAdaugareLectie() {
        System.out.print("Titlu curs: ");
        Curs c = cursService.cautaCursDupaTitlu(scanner.nextLine());

        if (c != null && c.getIdInstructor() == utilizatorLogat.getId()) {
            System.out.print("Titlu lectie: "); String t = scanner.nextLine();
            System.out.print("Durata (min): "); int d = citesteIntreg();

            if (d <= 0) {
                System.out.println("Eroare: Durata trebuie sa fie un numar valid si pozitiv");
                return;
            }

            System.out.print("Continut: "); String cont = scanner.nextLine();
            c.adaugaLectie(new Lectie(t, d, cont));
            System.out.println("Lectie adaugata");
        }

        else {
            System.out.println("Curs negasit sau nu ai permisiunea necesara");
        }
    }

    private void executaAdaugareQuiz() {
        System.out.print("Titlu curs: ");
        Curs c = cursService.cautaCursDupaTitlu(scanner.nextLine());

        if (c != null && c.getIdInstructor() == utilizatorLogat.getId()) {
            System.out.print("Titlu quiz: "); String t = scanner.nextLine();
            System.out.print("Numar intrebari: "); int nr = citesteIntreg();
            c.adaugaQuiz(new Quiz((int)(Math.random()*100) + 1, t, nr, 5.0));
            System.out.println("Quiz adaugat");
        }
    }

    private void executaModificarePret() {
        System.out.print("Titlu curs: ");
        Curs c = cursService.cautaCursDupaTitlu(scanner.nextLine());

        if (c != null && c.getIdInstructor() == utilizatorLogat.getId()) {
            System.out.print("Pret nou: ");
            c.setPret(Double.parseDouble(scanner.nextLine()));
            System.out.println("Pret actualizat");
        }
    }

    private void executaVizualizareRecenziiInstructor() {
        System.out.print("Titlu curs pentru recenzii: ");
        Curs c = cursService.cautaCursDupaTitlu(scanner.nextLine());

        if (c != null && c.getIdInstructor() == utilizatorLogat.getId()) {
            System.out.println("Recenzii pentru " + c.getTitlu());
            if (c.getRecenzii().isEmpty()) {
                System.out.println("Nicio recenzie inca");
            }

            else {
                c.getRecenzii().forEach(System.out::println);
            }
        }

        else System.out.println("Curs negasit sau nu iti apartine");
    }

    private void executaStergereCursPropriu() {
        System.out.print("Titlu curs de sters: ");
        String titlu = scanner.nextLine();
        Curs c = cursService.cautaCursDupaTitlu(titlu);

        if (c != null && c.getIdInstructor() == utilizatorLogat.getId()) {
            List<Integer> iduriQuiz = c.extrageIduriQuiz();
            cursService.stergeCurs(titlu);
            evaluareService.stergeDateAsociateCursului(c.getId(), iduriQuiz);
        }

        else {
            System.out.println("Eroare: Cursul nu exista sau nu iti apartine");
        }
    }

    private void executaInrolare() {
        System.out.print("Titlu curs dorit: ");
        Curs c = cursService.cautaCursDupaTitlu(scanner.nextLine());

        if (c != null && utilizatorLogat instanceof Cursant) {
            Cursant cursant = (Cursant) utilizatorLogat;

            if (evaluareService.esteDejaInrolat(cursant.getId(), c.getId())) {
                System.out.println("Esti deja inrolat la acest curs!");

                return;
            }

            try {
                if (cursant.getPortofelVirtual() < c.getPret()) {
                    throw new FonduriInsuficienteException("Sold insuficient");
                }
                cursant.setPortofelVirtual(cursant.getPortofelVirtual() - c.getPret());

                evaluareService.adaugaInrolare(new Inrolare(cursant.getId(), c.getId()));
                System.out.println("Te-ai inrolat cu succes la " + c.getTitlu());
            }

            catch (Exception e) { System.out.println(e.getMessage()); }
        }

        else System.out.println("Cursul nu a fost gasit");
    }

    private void executaSesiuneStudiu() {
        System.out.print("Introdu ID-ul inrolarii tale (verifica la optiunea 5 din meniu): ");
        int idInr = citesteIntreg();
        Inrolare inr = evaluareService.cautaInrolare(idInr);

        if (inr != null && inr.getIdCursant() == utilizatorLogat.getId()) {
            Curs curs = cursService.cautaCursDupaId(inr.getIdCurs());
            System.out.println("\nSesiune de studiu: " + curs.getTitlu());

            System.out.println("Lectii disponibile in curs:");
            if (curs.getLectii().isEmpty()) System.out.println("Nicio lectie disponibila inca");
            else curs.getLectii().forEach(l -> System.out.println("  - " + l.getTitlu() + " (" + l.getDurataMinute() + " min)"));

            System.out.println("\nQuiz-uri disponibile in curs:");
            if (curs.getQuizuri().isEmpty()) System.out.println("Niciun quiz disponibil inca");
            else curs.getQuizuri().forEach(q -> System.out.println("  - ID: " + q.getId() + " | Titlu: " + q.getTitlu()));

            System.out.println("\nAlegeri disponibile:");
            System.out.println("1. Parcurge o lectie");
            System.out.println("2. Sustine un quiz");
            System.out.println("3. Genereaza certificat (necesita 100% progres)");
            System.out.println("4. Lasa o recenzie cursului");
            System.out.print("Alege o actiune: ");

            int opt = citesteIntreg();
            switch (opt) {
                case 1 -> {
                    if (inr.getProgres() >= 100.0) {
                        System.out.println("Ai parcurs deja toate lectiile. Esti la zi cu acest curs (progres 100%)");
                    }

                    else if (curs.getLectii().isEmpty()) {
                        System.out.println("Nu ai ce lectii sa parcurgi momentan. Instructorul nu a adaugat continut");
                    }

                    else {
                        evaluareService.inregistreazaParcurgereLectie(inr, curs.getLectii().size());
                        System.out.println("Ai parcurs o lectie. Progresul tau a crescut la " + String.format("%.1f", inr.getProgres()) + "%");
                    }
                }
                case 2 -> {
                    if (curs.getQuizuri().isEmpty()) {
                        System.out.println("Nu exista quiz-uri pentru acest curs");
                        break;
                    }

                    System.out.print("Introdu ID-ul quiz-ului din lista de mai sus: ");
                    int idQ = citesteIntreg();

                    if (evaluareService.areQuizPromovat(utilizatorLogat.getId(), idQ)) {
                        System.out.println("Ai promovat deja acest quiz. Nu mai este necesar sa il sustii");
                        break;
                    }

                    double notaRandom = 1.0 + (Math.random() * 9.0);
                    System.out.println("Nota ta primita este: " + String.format("%.2f", notaRandom));

                    evaluareService.salveazaScorQuiz(utilizatorLogat.getId(), idQ, notaRandom);

                    if (notaRandom >= 5.0) {
                        System.out.println("Ai promovat testul cu succes!");
                    }

                    else {
                        System.out.println("Ai picat testul. Va trebui sa mai inveti");
                    }
                }
                case 3 -> {
                    if (inr.getProgres() >= 99.0) {
                        evaluareService.genereazaCertificat(utilizatorLogat.getNume(), curs.getTitlu(), inr.getProgres());
                    }

                    else {
                        System.out.println("Eroare: Cursul nu este finalizat. Progres actual: " + String.format("%.1f", inr.getProgres()) + "%");
                    }
                }
                case 4 -> {
                    if (inr.getProgres() >= 99.0) {
                        System.out.print("Nota acordata (1-5): ");
                        int rating = citesteIntreg();
                        System.out.print("Comentariu: ");
                        String com = scanner.nextLine();
                        curs.adaugaRecenzie(new Recenzie(utilizatorLogat.getId(), rating, com));
                        System.out.println("Recenzie salvata in sistem si vizibila pentru instructor");
                    }

                    else {
                        System.out.println("Trebuie sa termini cursul inainte de a lasa o recenzie.");
                    }
                }
                default -> System.out.println("Optiune invalida.");
            }
        }

        else {
            System.out.println("Inrolare negasita sau nu iti apartine");
        }
    }

    private void executaStergereInrolare() {
        System.out.print("Introdu ID-ul inrolarii la care vrei sa renunti: ");
        int idInr = citesteIntreg();

        Inrolare inr = evaluareService.cautaInrolare(idInr);

        if (inr != null && inr.getIdCursant() == utilizatorLogat.getId()) {
            Curs curs = cursService.cautaCursDupaId(inr.getIdCurs());

            List<Integer> iduriQuiz = curs.extrageIduriQuiz();

            evaluareService.stergeDateInrolare(idInr, utilizatorLogat.getId(), iduriQuiz);

            curs.stergeRecenzieCursant(utilizatorLogat.getId());

            System.out.println("Ai renuntat cu succes la cursul: " + curs.getTitlu());
        }

        else {
            System.out.println("Eroare: Inrolarea nu a fost gasita sau nu iti apartine");
        }
    }


    private void executaAlimentarePortofel() {
        System.out.print("Suma de adaugat: ");
        double suma = Double.parseDouble(scanner.nextLine());

        try {
            utilizatorService.alimenteazaPortofel(utilizatorLogat.getEmail(), suma);
        }

        catch (Exception e) { System.out.println(e.getMessage()); }
    }

    private void afiseazaClasamentQuiz() {
        System.out.println("\nClasament scoruri quiz");
        var grupari = evaluareService.grupeazaScoruriPeCursanti();

        if (grupari.isEmpty()) {
            System.out.println("Nu exista scoruri inregistrate");
        }

        else {
            grupari.forEach((idCursant, scoruri) -> {
                System.out.println("ID Cursant: " + idCursant);
                scoruri.forEach(scor -> System.out.println("  -> " + scor));
            });
        }
    }

    private int citesteIntreg() {
        try { return Integer.parseInt(scanner.nextLine()); }
        catch (Exception e) { return -1; }
    }
}