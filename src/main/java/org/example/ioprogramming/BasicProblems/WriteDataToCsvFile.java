package org.example.ioprogramming.BasicProblems;

import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class WriteDataToCsvFile {
    public static void main(String[] args) {
        try(CSVWriter writer = new CSVWriter(new FileWriter("student.csv",true))){
            String[] head = {"ID","Name","Department","Salary"};
            String[] emp1 = {"1","Shreya","HR","100000"};
            String[] emp2 = {"2","Anushree","Marketing","20000"};
            writer.writeAll(new ArrayList<>(Arrays.asList(head,emp1,emp2)));
            System.out.println("CSV file written successfully");
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
