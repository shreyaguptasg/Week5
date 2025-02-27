package org.ioprogramming.handsonpracticeproblem.convertcsvtojson;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.File;
import java.util.List;
import java.util.Map;

public class ConvertCsvToJson {
    public static void main(String[] args) {
        String filePath = "src/main/java/org/ioprogramming/handsonpracticeproblem/convertcsvtojson/data.csv";

        try {
            File csvFile = new File(filePath);

            CsvMapper csvMapper = new CsvMapper();
            CsvSchema schema = CsvSchema.emptySchema().withHeader();

            MappingIterator<Map<String, String>> iterator = csvMapper.readerFor(Map.class)
                    .with(schema)
                    .readValues(csvFile);

            List<Map<String, String>> data = iterator.readAll();

            ObjectMapper jsonMapper = new ObjectMapper();
            String jsonString = jsonMapper.writerWithDefaultPrettyPrinter().writeValueAsString(data);

            System.out.println(jsonString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
