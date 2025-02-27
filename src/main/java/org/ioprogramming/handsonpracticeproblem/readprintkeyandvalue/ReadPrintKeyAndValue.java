package org.ioprogramming.handsonpracticeproblem.readprintkeyandvalue;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class ReadPrintKeyAndValue {
    public static void main(String[] args) {
        String filePath = "src/main/java/org/ioprogramming/handsonpracticeproblem/readprintkeyandvalue/data.json";

        try {
            // Load JSON file
            File jsonFile = new File(filePath);

            // Create ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();

            // Read JSON file into JsonNode
            JsonNode rootNode = objectMapper.readTree(jsonFile);

            // Print all keys and values
            printJson(rootNode, "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Recursive method to print JSON keys and values
    private static void printJson(JsonNode node, String prefix) {
        if (node.isObject()) { // If JSON is an object
            for (Iterator<Map.Entry<String, JsonNode>> it = node.fields(); it.hasNext(); ) {
                Map.Entry<String, JsonNode> field = it.next();
                printJson(field.getValue(), prefix + field.getKey() + ".");
            }
        } else if (node.isArray()) { // If JSON is an array
            for (int i = 0; i < node.size(); i++) {
                printJson(node.get(i), prefix + i + ".");
            }
        } else { // If it's a value
            System.out.println(prefix.substring(0, prefix.length() - 1) + " = " + node.asText());
        }
    }
}
