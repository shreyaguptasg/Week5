package org.example.ioprogramming.AdvancedProblems;

import com.opencsv.CSVWriter;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Base64;

public class EncryptCSVData {

    private static SecretKey secretKey = generateSecretKey();

    public static void main(String[] args) {
        String csvFile = "encrypted_employees.csv";

        try (CSVWriter writer = new CSVWriter(new FileWriter(csvFile))) {
            String[] header = {"Employee ID", "Name", "Department", "Salary", "Email"};
            writer.writeNext(header);

            String[] record1 = {"1", "John Doe", "Engineering", encrypt("75000"), encrypt("john.doe@example.com")};
            String[] record2 = {"2", "Jane Smith", "HR", encrypt("55000"), encrypt("jane.smith@example.com")};

            writer.writeNext(record1);
            writer.writeNext(record2);

            System.out.println("CSV with encrypted data has been written.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String encrypt(String value) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encrypted = cipher.doFinal(value.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static SecretKey generateSecretKey() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128);
            return keyGenerator.generateKey();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

