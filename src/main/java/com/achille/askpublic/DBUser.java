/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.achille.askpublic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

/**
 *
 * @author achille
 */
public class DBUser {

    private static final String URL = "jdbc:mysql://localhost:3306/survey";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "password";

    public static Connection connect() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public static List<Map<String, Object>> get() throws ClassNotFoundException {

        try {
            Connection conn = DBUser.connect();
            String query = "SELECT * FROM users";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            List<Map<String, Object>> rows = new ArrayList<>();
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object value = rs.getObject(i);
                    row.put(columnName, value);
                }
                rows.add(row);
            }

            return rows;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public static ResultSet getOne(String email) throws ClassNotFoundException {
        try {
            Connection conn = DBUser.connect();
            String query = "SELECT * FROM users WHERE email='" + email + "'";
            PreparedStatement stmt = conn.prepareStatement(query);
            return stmt.executeQuery();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public static int add(String username, String email, String password) throws ClassNotFoundException, SQLException {
        try {
            Connection conn = DBUser.connect();
            String query = "INSERT INTO users (`user_name`, `email`, `password`) " +
                    "VALUES ('" + username + "', '" + email + "', '" + password + "')";
            PreparedStatement stmt = conn.prepareStatement(query);
            return stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    public static void update() {

    }

    public static int delete(String email) throws ClassNotFoundException, SQLException {
        try{
            Connection conn = DBUser.connect();
            String query = "DELETE FROM users WHERE email='"+email+"'";
            PreparedStatement stmt = conn.prepareStatement(query);
            return stmt.executeUpdate();
        }catch(SQLException e) {
            System.out.println(e);
        }
        return 0;
    }
}
