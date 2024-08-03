package com.jdbc;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpDAO {

    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/Emp";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    public boolean insertEmployee(Employee e) {
        String sql = "INSERT INTO emp_details (id, name, salary, dept, designation, doj, dob, photo, address) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, e.getId());
            pst.setString(2, e.getName());
            pst.setFloat(3, e.getSalary());
            pst.setString(4, e.getDept());
            pst.setString(5, e.getDesignation());
            pst.setDate(6, e.getDoj());
            pst.setDate(7, e.getDob());

            ByteArrayInputStream bais = new ByteArrayInputStream(e.getPhoto());
            pst.setBinaryStream(8, bais, e.getPhoto().length);

            pst.setString(9, e.getAddress());

            int result = pst.executeUpdate();
            return result > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public List<Employee> getAllEmployees() {
        String sql = "SELECT * FROM emp_details";
        List<Employee> employees = new ArrayList<>();
        try (Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                employees.add(new Employee(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getFloat("salary"),
                    rs.getString("dept"),
                    rs.getString("designation"),
                    rs.getDate("doj"),
                    rs.getDate("dob"),
                    rs.getBytes("photo"),
                    rs.getString("address")
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return employees;
    }

    public List<Employee> getEmployeesWithMoreThanTwoYearsExperience() {
        String sql = "SELECT * FROM emp_details WHERE doj <= DATE_SUB(CURDATE(), INTERVAL 2 YEAR)";
        List<Employee> employees = new ArrayList<>();
        try (Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                employees.add(new Employee(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getFloat("salary"),
                    rs.getString("dept"),
                    rs.getString("designation"),
                    rs.getDate("doj"),
                    rs.getDate("dob"),
                    rs.getBytes("photo"),
                    rs.getString("address")
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return employees;
    }

    public Employee getEmployeeById(int id) {
        String sql = "SELECT * FROM emp_details WHERE id = ?";
        try (Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return new Employee(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getFloat("salary"),
                        rs.getString("dept"),
                        rs.getString("designation"),
                        rs.getDate("doj"),
                        rs.getDate("dob"),
                        rs.getBytes("photo"),
                        rs.getString("address")
                    );
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean updateEmployeeSalary(int id, float newSalary) {
        String sql = "UPDATE emp_details SET salary = ? WHERE id = ?";
        try (Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setFloat(1, newSalary);
            pst.setInt(2, id);
            int result = pst.executeUpdate();
            return result > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean deleteEmployeeById(int id) {
        String sql = "DELETE FROM emp_details WHERE id = ?";
        try (Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, id);
            int result = pst.executeUpdate();
            return result > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}

