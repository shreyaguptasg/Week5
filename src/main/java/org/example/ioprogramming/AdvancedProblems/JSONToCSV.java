package org.example.ioprogramming.AdvancedProblems;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JSONToCSV {
    public static void main(String[] args) {
        String jsonFile = "students.json";
        String csvFile = "students.csv";

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<Map<String, Object>> students = objectMapper.readValue(new File(jsonFile), List.class);

            try (CSVWriter writer = new CSVWriter(new FileWriter(csvFile))) {
                // Write header
                String[] header = students.get(0).keySet().toArray(new String[0]);
                writer.writeNext(header);

                // Write data
                for (Map<String, Object> student : students) {
                    String[] record = student.values().toArray(new String[0]);
                    writer.writeNext(record);
                }

                System.out.println("JSON to CSV conversion completed.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

