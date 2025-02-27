package org.ioprogramming.handsonpracticeproblem;

import org.json.JSONObject;
import org.json.XML;

public class ConvertJsonToXML {
    public static void main(String[] args) {
        String jsonString = "{ \"name\": \"John\", \"age\": 25, \"city\": \"New York\" }";

        JSONObject jsonObject = new JSONObject(jsonString);

        String xml = XML.toString(jsonObject);

        System.out.println("<root>\n     " + xml + "\n</root>");
    }
}
