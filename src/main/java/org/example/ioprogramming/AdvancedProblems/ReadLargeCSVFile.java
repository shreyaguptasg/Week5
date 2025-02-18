package org.example.ioprogramming.AdvancedProblems;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadLargeCSVFile {
    public static void main(String[] args) {
        String inputFilePath = "large_students.csv";
        int batchSize = 100;
        int recordsProcessed = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            int count = 0;

            reader.readLine();

            while ((line = reader.readLine()) != null) {
                count++;

                if (count % batchSize == 0) {
                    recordsProcessed += batchSize;
                    System.out.println("Processed " + recordsProcessed + " records.");
                }
            }

            if (count % batchSize != 0) {
                recordsProcessed += count % batchSize;
                System.out.println("Processed " + recordsProcessed + " records.");
            }

            System.out.println("Finished processing the CSV file.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
