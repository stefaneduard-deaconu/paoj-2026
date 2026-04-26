package com.pao.project.catalog.service;

import com.pao.project.catalog.model.Nota;
import com.pao.project.catalog.exception.NotaInvalidaException;
import java.util.*;

public class CatalogService {
    private static CatalogService instance;
    // Map pentru indexare: cheia este ID-ul studentului, valoarea este lista de note
    private Map<String, List<Nota>> noteStudenti = new HashMap<>();

    private CatalogService() {}

    public static CatalogService getInstance() {
        if (instance == null) instance = new CatalogService();
        return instance;
    }

    public void adaugaNota(String studentId, Nota nota) {
        if (nota.getValoare() < 1 || nota.getValoare() > 10) {
            throw new NotaInvalidaException("Nota trebuie sa fie intre 1 si 10.");
        }
        noteStudenti.computeIfAbsent(studentId, k -> new ArrayList<>()).add(nota);
    }

    public List<Nota> getNoteStudent(String studentId) {
        return noteStudenti.getOrDefault(studentId, new ArrayList<>());
    }

    public double calculeazaMedia(String studentId) {
        List<Nota> note = noteStudenti.get(studentId);
        if (note == null || note.isEmpty()) return 0;
        return note.stream().mapToInt(Nota::getValoare).average().orElse(0);
    }
}