package org.example.ioprogramming.IntermediateProblems;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;

public class FliterRecords {
    public static void main(String[] args) {
        try(CSVReader reader = new CSVReader(new FileReader("student.csv"))) {
           String[] head =  reader.readNext();
           int minMarks =80;
           if(head==null){
               System.out.println("File is empty");
               return;
           }
           int marksIndex =-1;
           for(int i=0; i< head.length; i++){
               if(head[i].equalsIgnoreCase("Marks")){
                   marksIndex=i;
                   break;
               }
            }

           if(marksIndex==-1){
               System.out.println("Column marks does not exist");
               return;
           }
           String[] line;
            while ((line=reader.readNext()) != null) {
                if (line.length > marksIndex) {
                    int marks = Integer.parseInt(line[marksIndex]);
                    if (marks > minMarks) {
                        System.out.println(String.join(",", line));
                    }
                }
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }

}
