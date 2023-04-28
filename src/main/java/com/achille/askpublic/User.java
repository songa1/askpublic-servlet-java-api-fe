/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.achille.askpublic;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author achille
 */
public class User {
    private String username;
    private String email;
    private String password;
    private int id;

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean Login(String email, String password) throws ClassNotFoundException, SQLException {
        User user = new User();

        ResultSet userData = DBUser.getOne(email);

        while (userData.next()) {
            user.setId(userData.getInt("id"));
            user.setUsername(userData.getString("user_name"));
            user.setEmail(userData.getString("email"));
            user.setPassword(userData.getString("password"));
        }

        if (user.getEmail().equals(email)) {

            String passwordOne;
            passwordOne = user.getPassword();
            if (password.equals(passwordOne)) {
                return true;
            }

        }
        return false;
    }

    public Boolean Register() throws ClassNotFoundException, SQLException {
        int rowsAffected = DBUser.add(this.getUsername(), this.getEmail(), this.getPassword());

        return rowsAffected == 1;

    }

    /**
     * @return int return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

}
