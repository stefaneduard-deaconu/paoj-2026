package com.pao.project.platforma_elearning.service;

import com.pao.project.platforma_elearning.model.Certificat;
import com.pao.project.platforma_elearning.model.Inrolare;
import com.pao.project.platforma_elearning.model.ScorQuiz;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class EvaluareService {
    private static EvaluareService instance;
    private List<Inrolare> inrolari = new ArrayList<>();
    private List<ScorQuiz> scoruri = new ArrayList<>();

    private EvaluareService() {}

    public static EvaluareService getInstance() {
        if (instance == null) {
            instance = new EvaluareService();
        }

        return instance;
    }

    public void adaugaInrolare(Inrolare i) {
        inrolari.add(i);
        System.out.println("Inrolare inregistrata: " + i);
    }

    public boolean esteDejaInrolat(int idCursant, int idCurs) {
        for (Inrolare i : inrolari) {
            if (i.getIdCursant() == idCursant && i.getIdCurs() == idCurs) {
                return true;
            }
        }
        return false;
    }

    public Certificat genereazaCertificat(String numeCursant, String numeCurs, double progres) {
        if (progres >= 100.0) {
            String codUnic = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
            Certificat c = new Certificat(codUnic, numeCursant, numeCurs);
            System.out.println("Felicitari! Cod certificat: " + codUnic);

            return c;
        }

        System.out.println("Certificatul este primit doar dupa finalizarea cursului. Progres curent: " + progres + "%");

        return null;
    }

    public Inrolare cautaInrolare(int idInrolare) {
        for (Inrolare i : inrolari) {
            if (i.getIdInrolare() == idInrolare) {
                return i;
            }
        }

        return null;
    }

    public void salveazaScorQuiz(int idCursant, int idQuiz, double punctaj) {
        ScorQuiz scor = new ScorQuiz(idCursant, idQuiz, punctaj);
        this.scoruri.add(scor);
        System.out.println("Scorul de " + punctaj + " a fost inregistrat in sistem");
    }

    public Map<Integer, List<ScorQuiz>> grupeazaScoruriPeCursanti() {
        return scoruri.stream().collect(Collectors.groupingBy(ScorQuiz::getIdCursant));
    }

    public void afiseazaInrolariUtilizator(int idCursant) {
        System.out.println("\nIstoric Inrolari si Certificate");
        boolean gasit = false;

        for (Inrolare inr : inrolari) {
            if (inr.getIdCursant() == idCursant) {
                System.out.println(inr);
                gasit = true;

                if (inr.getProgres() >= 100.0) {
                    System.out.println("Curs finalizat! Poti cere generarea certificatului");
                }
            }
        }

        if (!gasit) {
            System.out.println("Nu esti inrolat la niciun curs");
        }
    }

    public boolean areQuizPromovat(int idCursant, int idQuiz) {
        for (ScorQuiz s : scoruri) {
            if (s.getIdCursant() == idCursant && s.getIdQuiz() == idQuiz && s.getPunctaj() >= 5.0) {
                return true;
            }
        }
        return false;
    }

    public void stergeDateAsociateCursului(int idCurs, List<Integer> iduriQuiz) {
        inrolari.removeIf(i -> i.getIdCurs() == idCurs);
        scoruri.removeIf(s -> iduriQuiz.contains(s.getIdQuiz()));
        System.out.println("Datele asociate cursului au fost sterse");
    }

    public void stergeDateInrolare(int idInrolare, int idCursant, List<Integer> iduriQuiz) {
        boolean eliminat = inrolari.removeIf(i -> i.getIdInrolare() == idInrolare);

        if (eliminat) {
            scoruri.removeIf(s -> s.getIdCursant() == idCursant && iduriQuiz.contains(s.getIdQuiz()));
            System.out.println("Inrolarea si scorurile asociate au fost eliminate definitiv din sistem");
        }

        else {
            System.out.println("Inrolarea cu ID-ul respectiv nu a fost gasita");
        }
    }

    public void inregistreazaParcurgereLectie(Inrolare inrolare, int numarTotalLectii) {
        if (numarTotalLectii == 0) return;

        double crestere = 100.0 / numarTotalLectii;
        double progresNou = inrolare.getProgres() + crestere;

        if (progresNou >= 99.9) {
            progresNou = 100.0;
        }

        inrolare.setProgres(progresNou);
    }
}