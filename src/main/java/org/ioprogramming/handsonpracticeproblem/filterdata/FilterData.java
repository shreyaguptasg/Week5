package org.ioprogramming.handsonpracticeproblem.filterdata;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.File;

public class FilterData {
    public static void main(String[] args) {
        String filePath = "src/main/java/org/ioprogramming/handsonpracticeproblem/filterdata/data.json";

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonArray = objectMapper.readTree(new File(filePath));

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
