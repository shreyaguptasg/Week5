package org.example.ioprogramming.AdvancedProblems;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

public class GenerateCSVReport {
    public static void main(String[] args) {
        String dbUrl = "jdbc:mysql://localhost:3306/your_database";
        String dbUsername = "your_username";
        String dbPassword = "your_password";
        String outputFile = "employee_report.csv";

        try (Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM employees");
             CSVWriter writer = new CSVWriter(new FileWriter(outputFile))) {

            String[] header = {"Employee ID", "Name", "Department", "Salary"};
            writer.writeNext(header);

            while (resultSet.next()) {
                String employeeId = resultSet.getString("employee_id");
                String name = resultSet.getString("name");
                String department = resultSet.getString("department");
                String salary = resultSet.getString("salary");

                String[] record = {employeeId, name, department, salary};
                writer.writeNext(record);
            }

            System.out.println("CSV report generated successfully.");

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
