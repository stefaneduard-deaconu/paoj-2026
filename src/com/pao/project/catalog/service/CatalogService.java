package com.pao.project.catalog.service;

import com.pao.project.catalog.model.Nota;
import com.pao.project.catalog.exception.NotaInvalidaException;
import com.pao.project.catalog.repository.NotaRepository;
import java.util.List;

public class CatalogService {
    private static CatalogService instance;
    private final NotaRepository notaRepository;
    private final AuditService auditService;

    private CatalogService() {
        this.notaRepository = new NotaRepository();
        this.auditService = AuditService.getInstance();
    }

    public static CatalogService getInstance() {
        if (instance == null) instance = new CatalogService();
        return instance;
    }

    // Aici s-a adaugat 'int materieId' pentru baza de date
    public void adaugaNota(String studentId, int materieId, Nota nota) {
        if (nota.getValoare() < 1 || nota.getValoare() > 10) {
            throw new NotaInvalidaException("Nota trebuie sa fie intre 1 si 10.");
        }
        notaRepository.saveNotaComplet(nota, studentId, materieId);
        auditService.logAction("adauga_nota");
    }

    public List<Nota> getNoteStudent(String studentId) {
        auditService.logAction("vizualizare_note_student");
        return notaRepository.getNoteByStudentId(studentId);
    }

    public double calculeazaMedia(String studentId) {
        auditService.logAction("calculare_medie");
        List<Nota> note = notaRepository.getNoteByStudentId(studentId);
        if (note == null || note.isEmpty()) return 0;
        return note.stream().mapToInt(Nota::getValoare).average().orElse(0);
    }
}