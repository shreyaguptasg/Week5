package org.example.ioprogramming.AdvancedProblems;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import java.io.FileReader;
import java.io.IOException;
import java.util.Base64;

public class DecryptCSVData {

    private static SecretKey secretKey = EncryptCSVData.generateSecretKey();

    public static void main(String[] args) {
        String csvFile = "encrypted_employees.csv";

        try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
            String[] line;
            reader.readNext(); // Skip header

            while ((line = reader.readNext()) != null) {
                String employeeId = line[0];
                String name = line[1];
                String department = line[2];
                String salary = decrypt(line[3]);
                String email = decrypt(line[4]);

                System.out.println("Employee ID: " + employeeId);
                System.out.println("Name: " + name);
                System.out.println("Department: " + department);
                System.out.println("Salary: " + salary);
                System.out.println("Email: " + email);
                System.out.println("----------------------------");
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }

    private static String decrypt(String encryptedValue) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(encryptedValue));
            return new String(decrypted);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

