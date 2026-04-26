package com.pao.project.catalog.model;

public class Student extends Persoana implements Comparable<Student> {
    private final IdentificatorScolar id;

    public Student(String nume, String email, IdentificatorScolar id) {
        super(nume, email);
        this.id = id;
    }

    @Override
    public String getRol() { return "STUDENT"; }

    public IdentificatorScolar getId() { return id; }

    @Override
    public int compareTo(Student o) {
        return this.nume.compareTo(o.nume); // sortare alfabetica
    }

    @Override
    public String toString() {
        return "Student{id=" + id + ", nume='" + nume + "'}";
    }
}