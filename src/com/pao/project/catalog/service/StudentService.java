package com.pao.project.catalog.service;

import com.pao.project.catalog.model.Student;
import com.pao.project.catalog.exception.StudentNegasitException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StudentService {
    private static StudentService instance;
    private List<Student> studenti = new ArrayList<>();

    private StudentService() {}

    public static StudentService getInstance() {
        if (instance == null) instance = new StudentService();
        return instance;
    }

    public void adaugaStudent(Student s) {
        if (s != null) studenti.add(s);
    }

    public void stergeStudent(String codId) {
        studenti.removeIf(s -> s.getId().getCod().equals(codId));
    }

    public List<Student> getStudentiSortati() {
        List<Student> copie = new ArrayList<>(studenti);
        Collections.sort(copie); // foloseste Comparable din Student
        return copie;
    }

    public Student cautaDupaId(String cod) throws StudentNegasitException {
        for (Student s : studenti) {
            if (s.getId().getCod().equals(cod)) return s;
        }
        throw new StudentNegasitException("Studentul cu ID " + cod + " nu a fost gasit.");
    }
}