package org.example.ioprogramming.AdvancedProblems;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

 class Student {
    private String id;
    private String name;
    private String department;
    private double salary;
    private int marks;

    public Student(String id, String name, String department, double salary, int marks) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "Student{id='" + id + "', name='" + name + "', department='" + department + "', salary=" + salary + ", marks=" + marks + '}';
    }
}

public class ConvertCSVToJavaObjects {
    public static void main(String[] args) {
        String inputFilePath = "student.csv";

        try (CSVReader reader = new CSVReader(new FileReader(inputFilePath))) {
            String[] header = reader.readNext();
            if (header == null) {
                System.out.println("File is empty");
                return;
            }

            List<Student> students = new ArrayList<>();
            String[] line;

            while ((line = reader.readNext()) != null) {
                String id = line[0];
                String name = line[1];
                String department = line[2];
                double salary = Double.parseDouble(line[3]);
                int marks = Integer.parseInt(line[4]);

                students.add(new Student(id, name, department, salary, marks));
            }

            for (Student student : students) {
                System.out.println(student);
            }

        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }
}
