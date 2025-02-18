package org.example.ioprogramming.BasicProblems;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;

public class ReadAndCountRows {
    public static void main(String[] args) {
        try(CSVReader reader = new CSVReader(new FileReader("student.csv"))){
            reader.readNext();
           int count =0;
           while((reader.readNext())!=null){
               count++;
           }
            System.out.println("Number of records in the student file is : " + count);
        }
        catch (IOException | CsvValidationException e){
            e.printStackTrace();
        }

    }
}
