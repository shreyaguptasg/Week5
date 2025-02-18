package org.example.ioprogramming.BasicProblems;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.*;


public class ReadAndPrintCSVFile {
    public static void main(String[] args) {
        String filePath="student.csv";

        try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
            String[] nextLine;
            while ((nextLine = csvReader.readNext()) != null) {
                System.out.println(String.join(", ", nextLine));
            }
        } catch (java.io.IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }
}
