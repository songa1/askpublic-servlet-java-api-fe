/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.achille.askpublic;

import com.google.gson.Gson;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author achille
 */
@WebServlet(name = "RegisterServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        Gson gson = new Gson();

        try {

            String username = req.getParameter("username");
            String password = req.getParameter("password");
            String email = req.getParameter("email");

            if (username == null || password == null || email == null) {
                res.setContentType("application/json");
                res.setCharacterEncoding("UTF-8");
                res.addHeader("Access-Control-Allow-Origin", "*");
                res.getWriter().println(gson.toJson(new ApiResponse<>(400, "Some information is missing.", null)));
            } else {
                User user = new User();

                user.setUsername(username);
                user.setEmail(email);
                user.setPassword(password);

                ResultSet userData = DBUser.getOne(email);

                if (userData.next()) {
                    res.setContentType("application/json");
                    res.setCharacterEncoding("UTF-8");
                    res.addHeader("Access-Control-Allow-Origin", "*");
                    res.getWriter().println(gson.toJson(new ApiResponse<>(400, "User already exists.", null)));

                } else {
                    boolean isRegistered = user.Register();
                    if (isRegistered) {
                        res.setContentType("application/json");
                        res.setCharacterEncoding("UTF-8");
                        res.addHeader("Access-Control-Allow-Origin", "*");
                        res.getWriter().println(gson.toJson(new ApiResponse<>(201, "User is registered.", null)));
                    } else {
                        res.setContentType("application/json");
                        res.setCharacterEncoding("UTF-8");
                        res.addHeader("Access-Control-Allow-Origin", "*");
                        res.getWriter().println(gson.toJson(new ApiResponse<>(500, "Error registering.", null)));
                    }
                }

            }
        } catch (IOException e) {
            res.setContentType("application/json");
            res.setCharacterEncoding("UTF-8");
            res.addHeader("Access-Control-Allow-Origin", "*");
            res.getWriter().println(gson.toJson(new ApiResponse<>(500, "Server error!", e)));
        } catch (SQLException ex) {
            res.setContentType("application/json");
            res.setCharacterEncoding("UTF-8");
            res.addHeader("Access-Control-Allow-Origin", "*");
            res.getWriter().println(gson.toJson(new ApiResponse<>(500, "Server error!", ex)));
        } catch (ClassNotFoundException ex) {
            res.setContentType("application/json");
            res.setCharacterEncoding("UTF-8");
            res.addHeader("Access-Control-Allow-Origin", "*");
            res.getWriter().println(gson.toJson(new ApiResponse<>(500, "Server error!", ex)));
        }
    }
}
