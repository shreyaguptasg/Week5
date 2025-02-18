package org.example.ioprogramming.IntermediateProblems;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;

public class SearchRecord {
    public static void main(String[] args) {
        try(CSVReader reader = new CSVReader(new FileReader("student.csv"))){
            String[] head = reader.readNext();
            String name = "Shreya";
            if(head==null){
                System.out.println("File is empty");
                return;
            }
            int nameIndex =-1;
            int deptIndex =-1;
            int salaryIndex =-1;
            for(int i=0; i< head.length; i++){
                if(head[i].equalsIgnoreCase("Name")){
                    nameIndex=i;
                }
                else if(head[i].equalsIgnoreCase("Department")){
                    deptIndex=i;
                }
                else if(head[i].equalsIgnoreCase("Salary")){
                    salaryIndex=i;
                }
            }

            if(nameIndex==-1|| salaryIndex==-1|| deptIndex==-1){
                System.out.println("Column does not exits");
                return;
            }

            String[] line;
            while((line = reader.readNext())!=null){
                if(line.length>nameIndex){
                    if(line[nameIndex].equals(name)){
                        System.out.println("Name : " + line[nameIndex]);
                        System.out.println("Department : " + line[deptIndex]);
                        System.out.println("Salary : " + line[salaryIndex]);
                    }
                }
            }


        }
        catch (IOException | CsvValidationException e){
            e.printStackTrace();
        }
    }
}
