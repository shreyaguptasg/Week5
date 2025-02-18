package org.example.ioprogramming.AdvancedProblems;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVToJSON {
    public static void main(String[] args) {
        String csvFile = "students.csv";
        String jsonFile = "students_output.json";

        try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
            List<String[]> records = reader.readAll();

            // Read header
            String[] header = records.get(0);

            List<Map<String, String>> students = new ArrayList<>();

            // Process each record
            for (int i = 1; i < records.size(); i++) {
                String[] row = records.get(i);
                Map<String, String> student = new HashMap<>();

                for (int j = 0; j < header.length; j++) {
                    student.put(header[j], row[j]);
                }

                students.add(student);
            }

            // Write JSON to file
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new FileWriter(jsonFile), students);

            System.out.println("CSV to JSON conversion completed.");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }
    }
}

