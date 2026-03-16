package com.pao.laboratory03.exercise.service;

import com.pao.laboratory03.exercise.exception.StudentNotFoundException;
import com.pao.laboratory03.exercise.model.Student;
import com.pao.laboratory03.exercise.model.Subject;
import java.util.*;

public class StudentService {
    private static StudentService instance;
    private List<Student> students = new ArrayList<>();

    private StudentService() {}

    public static StudentService getInstance() {
        if (instance == null) instance = new StudentService();
        return instance;
    }

    public void addStudent(String name, int age) {
        for (Student s : students) {
            if (s.getName().equalsIgnoreCase(name)) {
                throw new RuntimeException("Studentul " + name + " exista deja");
            }
        }
        students.add(new Student(name, age));
    }

    public Student findByName(String name) {
        for (Student s : students) {
            if (s.getName().equalsIgnoreCase(name)) return s;
        }
        throw new StudentNotFoundException("Studentul " + name + " nu a fost gasit");
    }

    public void addGrade(String studentName, Subject subject, double grade) {
        findByName(studentName).addGrade(subject, grade);
    }

    public void printAllStudents() {
        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);
            System.out.println((i + 1) + ". " + s);
            s.getGrades().forEach((sub, g) -> System.out.println("   " + sub.name() + " = " + g));
        }
    }

    public void printTopStudents() {
        List<Student> sorted = new ArrayList<>(students);
        sorted.sort((s1, s2) -> Double.compare(s2.getAverage(), s1.getAverage()));
        System.out.println("=== Top studenti ===");
        for (int i = 0; i < sorted.size(); i++) {
            System.out.println((i + 1) + ". " + sorted.get(i).getName() + " - media: " + String.format("%.2f", sorted.get(i).getAverage()));
        }
    }

    public Map<Subject, Double> getAveragePerSubject() {
        Map<Subject, Double> averages = new HashMap<>();
        for (Subject sub : Subject.values()) {
            double sum = 0;
            int count = 0;
            for (Student s : students) {
                if (s.getGrades().containsKey(sub)) {
                    sum += s.getGrades().get(sub);
                    count++;
                }
            }
            if (count > 0) averages.put(sub, sum / count);
        }
        return averages;
    }
}