package org.example.ioprogramming.AdvancedProblems;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateCSVData {
    public static void main(String[] args) {
        String inputFilePath = "student.csv";

        try (CSVReader reader = new CSVReader(new FileReader(inputFilePath))) {
            String[] header = reader.readNext();
            if (header == null) {
                System.out.println("File is empty");
                return;
            }

            int emailIndex = -1;
            int phoneIndex = -1;

            for (int i = 0; i < header.length; i++) {
                if (header[i].trim().equalsIgnoreCase("Email")) {
                    emailIndex = i;
                } else if (header[i].trim().equalsIgnoreCase("Phone Number")) {
                    phoneIndex = i;
                }
            }

            if (emailIndex == -1 || phoneIndex == -1) {
                System.out.println("Required columns (Email or Phone Number) are missing.");
                return;
            }

            String[] line;
            while ((line = reader.readNext()) != null) {
                boolean isValid = true;
                String email = line[emailIndex].trim();
                if (!isValidEmail(email)) {
                    System.out.println("Invalid Email: " + email + " in row: " + String.join(",", line));
                    isValid = false;
                }

                String phoneNumber = line[phoneIndex].trim();
                if (!isValidPhoneNumber(phoneNumber)) {
                    System.out.println("Invalid Phone Number: " + phoneNumber + " in row: " + String.join(",", line));
                    isValid = false;
                }

                if (isValid) {
                    System.out.println("Valid Record: " + String.join(",", line));
                }
            }

        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }

    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("\\d{10}");
    }
}

