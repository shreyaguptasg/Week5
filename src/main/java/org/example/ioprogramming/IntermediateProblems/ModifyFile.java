package org.example.ioprogramming.IntermediateProblems;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ModifyFile {
    public static void main(String[] args) {
        String inputFilePath = "student.csv";
        String outputFilePath = "updatedStudent.csv";

        try (CSVReader reader = new CSVReader(new FileReader(inputFilePath));
             CSVWriter writer = new CSVWriter(new FileWriter(outputFilePath))) {

            String[] header = reader.readNext();
            if (header == null) {
                System.out.println("File is empty");
                return;
            }

            writer.writeNext(header);

            int deptIndex = -1;
            int salaryIndex = -1;

            for (int i = 0; i < header.length; i++) {
                if (header[i].trim().equalsIgnoreCase("Department")) {
                    deptIndex = i;
                } else if (header[i].trim().equalsIgnoreCase("Salary")) {
                    salaryIndex = i;
                }
            }

            if (deptIndex == -1 || salaryIndex == -1) {
                System.out.println("One or more required columns are missing");
                return;
            }

            String[] line;
            while ((line = reader.readNext()) != null) {
                if (line[deptIndex].trim().equalsIgnoreCase("IT")) {
                    try {
                        double salary = Double.parseDouble(line[salaryIndex]);
                        salary *= 1.10; // Increase salary by 10%
                        line[salaryIndex] = String.format("%.2f", salary);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid salary format for employee: " + line[0]);
                    }
                }

                writer.writeNext(line);
            }

            System.out.println("Updated data saved to " + outputFilePath);

        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }
}
