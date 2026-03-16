package com.pao.laboratory03.exercise.model;

import com.pao.laboratory03.exceptions.InvalidAgeException;
import com.pao.laboratory03.exercise.exception.InvalidStudentException;
import com.pao.laboratory03.exercise.exception.InvalidGradeException;
import java.util.HashMap;
import java.util.Map;

public class Student {
    private String name;
    private int age;
    private Map<Subject, Double> grades;

    public Student(String name, int age){
        this.grades = new HashMap<>();
        this.name = name;
        this.age = age;

        if(age < 18 || age > 60)
            throw new InvalidAgeException("Vârsta " + age + " este invalidă pentru un student (18-60).");
    }

    public String getName() {return name;}
    public int getAge() { return age; }
    public Map<Subject, Double> getGrades() {return grades;}

    public void addGrade(Subject subject, double grade){
        if(grade < 1 || grade > 10) throw new InvalidGradeException("Nota " + grade + " trebuie să fie între 1 și 10.");

        grades.put(subject, grade);
    }

    public double getAverage(){
        if(grades.isEmpty())
            return 0.0;

        double sum = 0;
        for(double g : grades.values()){
            sum += g;
        }
        return sum/grades.size();
    }

    @Override
    public String toString(){ return String.format("Student{name='%s', age=%d, avg=%.2f}", name, age, getAverage());}
}