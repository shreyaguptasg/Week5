package org.ioprogramming.practiceproblem.filterrecords;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.File;

public class FilterRecords {
    public static void main(String[] args) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonArray = objectMapper.readTree(new File("src/main/java/org/ioprogramming/practiceproblem/filterrecords/data.json"));

            ArrayNode flterArray = objectMapper.createArrayNode();

            for(JsonNode node : jsonArray){
                if(node.get("age").asInt()>25){
                    flterArray.add(node);
                }
            }
            String filterJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(flterArray);
            System.out.println(filterJson);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
