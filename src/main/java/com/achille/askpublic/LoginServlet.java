/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.achille.askpublic;

import com.google.gson.Gson;
import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author achille
 */
@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        Gson gson = new Gson();

        try {
            String email = req.getParameter("email");
            String password = req.getParameter("password");

            ResultSet userData = DBUser.getOne(email);

            if (userData != null) {
                User user = new User();

                while (userData.next()) {
                    user.setId(userData.getInt("id"));
                    user.setUsername(userData.getString("user_name"));
                    user.setEmail(userData.getString("email"));
                    user.setPassword(userData.getString("password"));
                }

                boolean isLogin = user.Login(email, password);

                if (isLogin) {
                    res.setContentType("application/json");
                    res.setCharacterEncoding("UTF-8");
                    res.addHeader("Access-Control-Allow-Origin", "*");
                    res.getWriter()
                            .println(gson.toJson(new ApiResponse<>(200, "Login successful!", user.getUsername())));
                } else {
                    res.setContentType("application/json");
                    res.setCharacterEncoding("UTF-8");
                    res.addHeader("Access-Control-Allow-Origin", "*");
                    res.getWriter().println(gson.toJson(new ApiResponse<>(400, "Wrong password", 0)));
                }
            } else {
                res.setContentType("application/json");
                res.setCharacterEncoding("UTF-8");
                res.addHeader("Access-Control-Allow-Origin", "*");
                res.getWriter()
                        .println(gson.toJson(new ApiResponse<>(404, "User not found!", 404)));
            }

        } catch (Exception e) {
            res.setContentType("application/json");
            res.setCharacterEncoding("UTF-8");
            res.addHeader("Access-Control-Allow-Origin", "*");
            res.getWriter().println(gson.toJson(new ApiResponse<>(500, "Error while loggin in!", e)));
        }
    }
}
