package com.pao.laboratory03.exercise.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pao.laboratory03.exercise.model.Student;
import com.pao.laboratory03.exercise.model.Subject;

public class StudentService {
    List<Student> students = new ArrayList<>();

    private static StudentService instance;

    private StudentService() {

    }

    public static StudentService getInstance() {
        if (instance == null) {
            instance = new StudentService();
        }
        return instance;
    }

    public void addStudent(String name, int age) {
        Student student = new Student(name, age);

        for (Student s : students) {
            if (s.getName() == student.getName()) {
                throw new IllegalArgumentException("Student cu id " + student.getName() + " exista deja");
            }
        }
        students.add(student);
    }

    public Student findByName(String name) {
        for (Student s : students) {
            if (s.getName().equals(name)) {
                return s;
            }
        }
        throw new IllegalArgumentException("Studentul " + name + " nu a fost gasit");
    }

    public void addGrade(String studentName, Subject subject, double grade) {
        Student student = findByName(studentName);
        student.addGrade(subject, grade);
    }

    public void printAllStudents() {
        for (Student s : students) {
            System.out.println(s);
            s.getGrades().forEach((subject, grade) -> {
                System.out.println("  " + subject + ": " + grade);
            });
        }
    }

    public void printTopStudents() {
        students.sort((s1, s2) -> Double.compare(s2.getAverage(), s1.getAverage()));
        for (Student s : students) {
            System.out.println(s);
        }
    }

    public Map<Subject, Double> getAveragePerSubject() {
        Map<Subject, Double> averages = new HashMap<>();
        averages.put(Subject.PAOJ, 0.0);
        averages.put(Subject.BD, 0.0);
        averages.put(Subject.SO, 0.0);
        averages.put(Subject.RC, 0.0);

        for (Student student : students) {
            for (Map.Entry<Subject, Double> entry : student.getGrades().entrySet()) {
                Subject subject = entry.getKey();
                Double grade = entry.getValue();
                averages.put(subject, averages.get(subject) + grade);
            }
        }
        averages.replaceAll((subject, total) -> total / students.size());
        return averages;
    }
}
