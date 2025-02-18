package org.example.ioprogramming.IntermediateProblems;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class SortRecordsByColumn {
    public static void main(String[] args) {
        String inputFilePath = "student.csv";

        try (CSVReader reader = new CSVReader(new FileReader(inputFilePath))) {
            String[] header = reader.readNext();
            if (header == null) {
                System.out.println("File is empty");
                return;
            }

            int salaryIndex = -1;

            for (int i = 0; i < header.length; i++) {
                if (header[i].trim().equalsIgnoreCase("Salary")) {
                    salaryIndex = i;
                    break;
                }
            }

            if (salaryIndex == -1) {
                System.out.println("Salary column does not exist.");
                return;
            }

            List<String[]> records = new ArrayList<>();
            String[] line;

            while ((line = reader.readNext()) != null) {
                records.add(line);
            }

            final int finalSalaryIndex = salaryIndex;  // Make it effectively final

            records.sort((record1, record2) -> {
                try {
                    double salary1 = Double.parseDouble(record1[finalSalaryIndex]);
                    double salary2 = Double.parseDouble(record2[finalSalaryIndex]);
                    return Double.compare(salary2, salary1);
                } catch (NumberFormatException e) {
                    return 0;
                }
            });

            for (int i = 0; i < Math.min(5, records.size()); i++) {
                System.out.println(String.join(",", records.get(i)));
            }

        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }
}
