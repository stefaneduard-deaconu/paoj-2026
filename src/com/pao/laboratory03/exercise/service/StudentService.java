package com.pao.laboratory03.exercise.service;

import com.pao.laboratory03.exercise.model.Student;
import com.pao.laboratory03.exercise.model.Subject;

import com.pao.laboratory03.exercise.exception.StudentNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentService {
    private static StudentService instance;

    List<Student> students;

    private StudentService(){
        this.students = new ArrayList<>();
    }

    public static StudentService getInstance() {
        if (instance == null) {
            instance = new StudentService();
        }
        return instance;
    }

    public void addStudent(String nume, int age){
        for(Student s : students){
            if(s.getName().equalsIgnoreCase(nume))
                throw new RuntimeException("Studentul exista deja: " + nume);
        }
        students.add(new Student(nume, age));
    }

    public Student findByName(String nume) {
        for(Student s : students){
            if(s.getName().equalsIgnoreCase(nume)) {
                System.out.println("Studentul: " + nume + " exista");
                return s;
            }
        }
        throw new StudentNotFoundException("Studentul " + nume + " nu exista");
    }

    public void addGrade(String studentName, Subject subject, double grade){
        Student s = findByName(studentName);
        s.addGrade(subject, grade);
    }

    public void printAllStudents(){
        for(Student s : students){
            System.out.println(s.getName() + s.getGrades());
        }
    }

    public void printTopStudents(){
        List<Student> sortedStudents = new ArrayList<>(students);
        sortedStudents.sort((s1, s2) -> Double.compare(s2.getAverage(), s1.getAverage()));
        sortedStudents.forEach(System.out::println);
    }

    public Map<Subject, Double> getAveragePerSubject() {
        Map<Subject, Double> sumMap = new HashMap<>();
        Map<Subject, Integer> countMap = new HashMap<>();

        for (Student s : students) {
            for (Map.Entry<Subject, Double> entry : s.getGrades().entrySet()) {
                Subject sub = entry.getKey();
                double grade = entry.getValue();

                sumMap.put(sub, sumMap.getOrDefault(sub, 0.0) + grade);
                countMap.put(sub, countMap.getOrDefault(sub, 0) + 1);
            }
        }

        Map<Subject, Double> averages = new HashMap<>();
        sumMap.forEach((subject, sum) -> {
            averages.put(subject, sum / countMap.get(subject));
        });

        return averages;
    }
}