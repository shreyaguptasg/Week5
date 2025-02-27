package org.ioprogramming.practiceproblem.readandextractfields;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReadAndExtract {
    public static void main(String[] args) {
        String filePath = "src/main/java/org/ioprogramming/practiceproblem/readandextractfields/user.json";
        try {
            File jsonFile = new File(filePath);

            ObjectMapper objectMapper = new ObjectMapper();

            JsonNode rootNode = objectMapper.readTree(jsonFile);

            List<Map<String, String>> extractedData = new ArrayList<>();

            if (rootNode.isArray()) {
                for (JsonNode node : rootNode) {
                    String name = node.has("name") ? node.get("name").asText() : null;
                    String email = node.has("email") ? node.get("email").asText() : null;

                    extractedData.add(Map.of("name", name, "email", email));
                }
            }

            String filteredJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(extractedData);

            System.out.println(filteredJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
