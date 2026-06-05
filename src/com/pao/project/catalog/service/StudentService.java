package com.pao.project.catalog.service;

import com.pao.project.catalog.model.Student;
import com.pao.project.catalog.exception.StudentNegasitException;
import com.pao.project.catalog.repository.StudentRepository;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class StudentService {
    private static StudentService instance;
    private final StudentRepository studentRepository;
    private final AuditService auditService;

    private StudentService() {
        this.studentRepository = new StudentRepository();
        this.auditService = AuditService.getInstance();
    }

    public static StudentService getInstance() {
        if (instance == null) instance = new StudentService();
        return instance;
    }

    public void adaugaStudent(Student s) {
        if (s != null) {
            studentRepository.save(s);
            auditService.logAction("adauga_student");
        }
    }

    public void stergeStudent(String codId) {
        studentRepository.delete(codId);
        auditService.logAction("sterge_student");
    }

    public Student cautaDupaId(String cod) throws StudentNegasitException {
        auditService.logAction("cauta_student_id");
        Optional<Student> studentOpt = studentRepository.findById(cod);
        return studentOpt.orElseThrow(() -> new StudentNegasitException("Studentul cu ID " + cod + " nu a fost gasit."));
    }

    public List<Student> getStudentiSortati() {
        auditService.logAction("listeaza_studenti_sortati");
        List<Student> list = studentRepository.findAll();
        Collections.sort(list); // Bifează cerința de colecție sortată
        return list;
    }

    // --- METODA PENTRU ETAPA 1: Utilizare Map pentru indexare ---
    public Map<String, Student> getStudentiIndexati() {
        auditService.logAction("indexare_studenti_map");
        List<Student> totiStudentii = studentRepository.findAll();
        Map<String, Student> mapStudenti = new HashMap<>();

        for (Student s : totiStudentii) {
            mapStudenti.put(s.getId().getCod(), s); // Indexare după ID-ul studentului
        }
        return mapStudenti;
    }
}