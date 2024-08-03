package com.jdbc;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Convert the image file to a byte array
        byte[] photo = null;
        try {
            File file = new File("C:\\Users\\ANITA\\OneDrive\\Pictures\\IMG_20220731_172804.jpg");
            photo = Files.readAllBytes(file.toPath());
        } catch (IOException ex) {
            ex.printStackTrace();
            return;
        }

        // Create an Employee object with the byte array for the photo
        Employee e = new Employee(0, "Janani", 90000, "Tech", "Admin", Date.valueOf("2024-09-23"), Date.valueOf("2004-04-12"), photo, "Karur");

        // Insert the Employee object into the database
        EmpDAO ed = new EmpDAO();
        boolean result = ed.insertEmployee(e);

        if (result) {
            System.out.println("Employee inserted successfully");
        } else {
            System.out.println("Failed to insert employee");
        }

        // Retrieve all employees
        List<Employee> allEmployees = ed.getAllEmployees();
        System.out.println("\nAll Employees:");
        for (Employee emp : allEmployees) {
            System.out.println(emp.getName() + " - " + emp.getDept());
        }

        // Retrieve employees having 2 or more years of experience
        List<Employee> experiencedEmployees = ed.getEmployeesWithMoreThanTwoYearsExperience();
        System.out.println("\nEmployees having 2 or more years of experience:");
        for (Employee emp : experiencedEmployees) {
            System.out.println(emp.getName() + " - " + emp.getDept());
        }

        // Get employee by ID
        Employee employee = ed.getEmployeeById(2);
        if (employee != null) {
            System.out.println("\nEmployee with ID 2: " + employee.getName());
        } else {
            System.out.println("\nEmployee with ID 2 not found.");
        }

        // Update employee salary
        boolean isUpdated = ed.updateEmployeeSalary(2, 250000);
        if (isUpdated) {
            System.out.println("\nEmployee salary updated successfully.");
        } else {
            System.out.println("\nFailed to update employee salary.");
        }
    }
}
