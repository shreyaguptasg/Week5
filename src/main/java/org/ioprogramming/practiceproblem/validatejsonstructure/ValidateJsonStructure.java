package org.ioprogramming.practiceproblem.validatejsonstructure;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class ValidateJsonStructure {
    public static void main(String[] args) {


        String jsonString = "src/main/java/org/ioprogramming/practiceproblem/validatejsonstructure/data.json";

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode jsonNode = objectMapper.readTree(new File(jsonString));
            System.out.println("Valid json : " + jsonNode);
        } catch (Exception e) {
            System.out.println("Invalid json : " + e.getMessage());
        }
    }
}
