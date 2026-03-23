package com.pao.laboratory03.exercise.model;

import java.util.HashMap;
import java.util.Map;

import com.pao.laboratory03.exercise.exception.*;

public class Student {
    private String name;
    private int age;
    private Map<Subject, Double> grades;

    public Student(String name, int age) {
        if (age < 18 || age > 60) {
            throw new InvalidStudentException("Varsta studentului trebuie sa fie intre 18 si 60 de ani");
        }
        this.name = name;
        this.age = age;
        this.grades = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Map<Subject, Double> getGrades() {
        return grades;
    }

    public void addGrade(Subject subject, double grade) {
        if (grade < 1 || grade > 10) {
            throw new InvalidGradeException("Nota trebuie sa fie între 1 si 10");
        }
        grades.put(subject, grade);
    }

    public double getAverage() {
        if (grades.isEmpty()) {
            return 0;
        }
        double sum = 0;
        for (double grade : grades.values()) {
            sum += grade;
        }
        return sum / grades.size();
    }

    @Override
    public String toString() {
        return "Student{name='" + name + "', age=" + age + ", avg=" + String.format("%.2f", getAverage()) + "}";
    }
}
