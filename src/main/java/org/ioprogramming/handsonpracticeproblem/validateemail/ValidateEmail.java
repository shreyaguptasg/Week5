package org.ioprogramming.handsonpracticeproblem.validateemail;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;

import java.io.File;

public class ValidateEmail {
    public static void main(String[] args) {
        String schemaFilePath = "src/main/java/org/ioprogramming/handsonpracticeproblem/validateemail/schema.json";
        String dataFilePath = "src/main/java/org/ioprogramming/handsonpracticeproblem/validateemail/data.json";
        try {

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode schemaNode = objectMapper.readTree(new File(schemaFilePath));
            JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
            JsonSchema schema = factory.getJsonSchema(schemaNode);


            JsonNode jsonData = objectMapper.readTree(new File(dataFilePath));

            ProcessingReport report = schema.validate(jsonData);

            // ✅ Print Validation Result
            if (report.isSuccess()) {
                System.out.println("JSON is valid!");
            } else {
                System.out.println("Invalid JSON!");
                report.forEach(msg -> System.out.println(msg));
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
