package org.example.ioprogramming.AdvancedProblems;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MergeCSVFiles {
    public static void main(String[] args) {
        String studentsFile1 = "students1.csv";
        String studentsFile2 = "students2.csv";
        String outputFile = "merged_students.csv";

        try (CSVReader reader1 = new CSVReader(new FileReader(studentsFile1));
             CSVReader reader2 = new CSVReader(new FileReader(studentsFile2));
             CSVWriter writer = new CSVWriter(new FileWriter(outputFile))) {

            Map<String, String[]> students1Data = new HashMap<>();
            String[] line;

            reader1.readNext(); // Skip header
            while ((line = reader1.readNext()) != null) {
                students1Data.put(line[0], line); // Put ID as key and the whole row as value
            }

            String[] header2 = reader2.readNext(); // Read header from the second file
            String[] mergedHeader = new String[header2.length + 2];
            System.arraycopy(header2, 0, mergedHeader, 0, header2.length);
            mergedHeader[header2.length] = "Name";
            mergedHeader[header2.length + 1] = "Age";

            writer.writeNext(mergedHeader); // Write merged header

            reader2.readNext(); // Skip header of the second file
            while ((line = reader2.readNext()) != null) {
                String id = line[0];
                String[] student1Data = students1Data.get(id);
                if (student1Data != null) {
                    String[] mergedData = new String[line.length + student1Data.length - 1];
                    System.arraycopy(line, 0, mergedData, 0, line.length); // Copy data from students2.csv
                    System.arraycopy(student1Data, 1, mergedData, line.length, student1Data.length - 1); // Copy Name and Age from students1.csv
                    writer.writeNext(mergedData); // Write merged data to the new file
                }
            }

            System.out.println("CSV files merged successfully into " + outputFile);

        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }
}
