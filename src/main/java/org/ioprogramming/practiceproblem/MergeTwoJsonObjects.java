package org.ioprogramming.practiceproblem;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class MergeTwoJsonObjects {
    public static void main(String[] args) {
        try {
            // Create ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();

            // JSON String 1
            String json1 = "{ \"name\": \"Pratham\", \"age\": 28 }";

            // JSON String 2
            String json2 = "{ \"email\": \"Pratham@123\", \"city\": \"Mumbai\" }";

            // Convert strings to JsonNode
            JsonNode node1 = objectMapper.readTree(json1);
            JsonNode node2 = objectMapper.readTree(json2);

            // Merge JSON objects
            ObjectNode mergedNode = objectMapper.createObjectNode();
            mergedNode.setAll((ObjectNode) node1);
            mergedNode.setAll((ObjectNode) node2);

            // Convert merged JSON to string
            String mergedJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(mergedNode);

            // Print merged JSON
            System.out.println(mergedJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
