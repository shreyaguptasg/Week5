package org.ioprogramming.practiceproblem;

import com.fasterxml.jackson.databind.ObjectMapper;


class JavaObject {
    public String name;
    public String model;
    public int modelNumber;

    public JavaObject(String name, String model, int modelNumber) {
        this.name = name;
        this.model = model;
        this.modelNumber = modelNumber;
    }
}

public class ConvertJavaToJson {
    public static void main(String[] args) {
        try{
            ObjectMapper obm = new ObjectMapper();
            JavaObject user = new JavaObject("Alto","Car",101);
            String jsonString = obm.writeValueAsString(user);
            System.out.println(jsonString);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
