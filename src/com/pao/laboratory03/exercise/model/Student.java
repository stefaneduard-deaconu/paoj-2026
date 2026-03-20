package com.pao.laboratory03.exercise.model;

import com.pao.laboratory03.exercise.exception.InvalidGradeException;
import com.pao.laboratory03.exercise.exception.InvalidStudentException;

import java.util.HashMap;
import java.util.Map;

//  2. model/Student.java — CLASĂ
//  - Câmpuri private: String name, int age, Map<Subject, Double> grades
//  - Constructor: Student(String name, int age)
//  → inițializează grades ca HashMap gol
//  → validare: dacă age < 18 sau age > 60, aruncă InvalidStudentException
//  - Metode: getName(), getAge(), getGrades()
//  - addGrade(Subject subject, double grade)
//  → dacă grade < 1 sau grade > 10, aruncă InvalidGradeException
//  → pune nota în map (suprascrie dacă materia există deja)
//  - double getAverage()
//  → calculează media aritmetică a notelor (returnează 0 dacă nu are note)
//  - toString() → "Student{name='Ana', age=20, avg=8.50}"
public class Student {

    public String toString(){
        return "Student{name='" + name + "', age=" + age + ", avg=" + String.format("%.2f", getAverage()) + "}";
    }

    public double getAverage() {
        if (grades.isEmpty()) return 0;

        double sum = 0;
        for (double grade : grades.values()) {
            sum += grade;
        }
        return sum / grades.size();
    }

    public void addGrade(Subject subject, double grade){
        if (grade < 1 || grade > 10)
            throw new InvalidGradeException("Nota " + grade + " nu este valida! (1-10)");
        grades.put(subject, grade);
    }

    public String getName(){
        return this.name;
    }

    public int getAge(){
        return this.age;
    }

    public Map<Subject, Double> getGrades(){
        return this.grades;
    }

    private String name;
    private int age;
    private Map<Subject, Double> grades;

    public Student(String name, int age){
        if(age < 18 || age > 60)
            throw new InvalidStudentException("Studentul " + name + "nu are o varsta valida! (18-60)");
        this.name = name;
        this.age = age;
        this.grades = new HashMap<Subject, Double>();
    }
}
