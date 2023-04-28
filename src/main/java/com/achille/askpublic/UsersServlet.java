package com.achille.askpublic;

import java.io.IOException;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet(name = "UsersServlet", urlPatterns = "/users")
public class UsersServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        Gson gson = new Gson();

        try {
            List<Map<String, Object>> users = DBUser.get();

            res.setContentType("application/json");
            res.setCharacterEncoding("UTF-8");
            res.addHeader("Access-Control-Allow-Origin", "*");
            res.getWriter().println(gson.toJson(new ApiResponse<>(200, "Got all users", users)));
        } catch (Exception e) {
            res.setContentType("application/json");
            res.setCharacterEncoding("UTF-8");
            res.addHeader("Access-Control-Allow-Origin", "*");
            res.getWriter().println(gson.toJson(new ApiResponse<>(500, "Error while getting users in!", e)));
        }
    }
}
