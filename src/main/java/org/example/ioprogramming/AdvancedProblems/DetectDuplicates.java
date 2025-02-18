package org.example.ioprogramming.AdvancedProblems;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class DetectDuplicates {
    public static void main(String[] args) {
        String inputFilePath = "student.csv";
        Set<String> seenIds = new HashSet<>();
        Set<String> duplicateIds = new HashSet<>();

        try (CSVReader reader = new CSVReader(new FileReader(inputFilePath))) {
            String[] line;
            reader.readNext(); // Skip the header

            while ((line = reader.readNext()) != null) {
                String id = line[0];

                if (!seenIds.add(id)) {
                    duplicateIds.add(id);
                }
            }

            if (duplicateIds.isEmpty()) {
                System.out.println("No duplicates found.");
            } else {
                reader.close();
                try (CSVReader reader2 = new CSVReader(new FileReader(inputFilePath))) {
                    String[] header = reader2.readNext();
                    System.out.println(String.join(",", header));

                    while ((line = reader2.readNext()) != null) {
                        String id = line[0];
                        if (duplicateIds.contains(id)) {
                            System.out.println(String.join(",", line));
                        }
                    }
                }
            }

        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }
}

