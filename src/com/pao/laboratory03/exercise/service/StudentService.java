package com.pao.laboratory03.exercise.service;

// 6. service/StudentService.java — SERVICIU (Singleton)
//    - Câmp: List<Student> students (ArrayList)
//    - Singleton pattern (constructor privat, getInstance())
//      *    - Metode:
//      *      a) void addStudent(String name, int age)
//         → creează Student și adaugă în listă
//         → dacă există deja un student cu același nume, aruncă RuntimeException
//      b) Student findByName(String name)
//         → caută în listă, aruncă StudentNotFoundException dacă nu găsește
//      c) void addGrade(String studentName, Subject subject, double grade)
//         → găsește studentul (findByName) și adaugă nota
//      d) void printAllStudents()
//         → afișează toți studenții cu notele lor
//      e) void printTopStudents()
//         → sortează studenții descrescător după medie și afișează
//      f) Map<Subject, Double> getAveragePerSubject()

import com.pao.laboratory03.exercise.exception.StudentNotFoundException;
import com.pao.laboratory03.exercise.model.Student;
import com.pao.laboratory03.exercise.model.Subject;

import java.util.*;

public class StudentService {

    public Map<Subject, Double> getAveragePerSubject() {
        Map<Subject, Double> sumMap = new HashMap<>();
        Map<Subject, Integer> countMap = new HashMap<>();

        for (Student student : students) {
            for (Map.Entry<Subject, Double> entry : student.getGrades().entrySet()) {
                Subject subject = entry.getKey();
                double grade = entry.getValue();
                sumMap.put(subject, sumMap.getOrDefault(subject, 0.0) + grade);
                countMap.put(subject, countMap.getOrDefault(subject, 0) + 1);
            }
        }

        Map<Subject, Double> averageMap = new HashMap<>();
        for (Subject subject : sumMap.keySet()) {
            averageMap.put(subject, sumMap.get(subject) / countMap.get(subject));
        }

        return averageMap;
    }

    public void printTopStudents() {
        List<Student> sorted = new ArrayList<>(students);
        sorted.sort((a, b) -> Double.compare(b.getAverage(), a.getAverage()));
        for (Student student : sorted)
            System.out.println(student);
    }

    public void printAllStudents(){
        for(Student student : students){
            Map<Subject, Double> grades = student.getGrades();
            System.out.println(student.toString());
            for(Map.Entry<Subject, Double> grade : grades.entrySet())
                System.out.println(grade.getKey() + ": " + grade.getValue());
        }
    }

    public void addGrade(String studentName, Subject subject, double grade){
        Student student = findByName(studentName);
        student.addGrade(subject, grade);
    }

    public Student findByName(String name){
        for(Student student : students){
            if(Objects.equals(student.getName(), name)){
                return student;
            }
        }
        throw new StudentNotFoundException("No student with the name '" + name + "' could be found.");
    }

    public void addStudent(String name, int age){
        Student student = new Student(name, age);
        for(Student s : students){
            if (Objects.equals(s.getName(), name)){
                throw new RuntimeException("Student with the name '" + name + "' already exists!");
            }
        }
        students.add(student);
    }

    public static StudentService getInstance(){
        return instance;
    }

    private static StudentService instance = new StudentService();
    private List<Student> students;

    private StudentService(){
        students = new ArrayList<Student>();
    }
}
