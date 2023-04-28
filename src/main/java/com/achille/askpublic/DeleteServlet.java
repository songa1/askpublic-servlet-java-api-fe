/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.achille.askpublic;

import com.google.gson.Gson;
import java.io.IOException;
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
@WebServlet(name = "DeleteServlet", urlPatterns = "/users/delete")
public class DeleteServlet extends HttpServlet{
   
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        Gson gson = new Gson();
        try {
            String email = req.getParameter("email");
            int deletedData = DBUser.delete(email);
            
            if(deletedData > 0){
                res.setContentType("application/json");
                res.setCharacterEncoding("UTF-8");
                res.addHeader("Access-Control-Allow-Origin", "*");
                res.getWriter().println(gson.toJson(new ApiResponse<>(200, "User deleted", 1)));
            }else{
                res.setContentType("application/json");
                res.setCharacterEncoding("UTF-8");
                res.addHeader("Access-Control-Allow-Origin", "*");
                res.getWriter().println(gson.toJson(new ApiResponse<>(400, "Error deleting the user!", 0)));
            }
        }catch(IOException | ClassNotFoundException | SQLException e) {
            res.setContentType("application/json");
            res.setCharacterEncoding("UTF-8");
            res.addHeader("Access-Control-Allow-Origin", "*");
            res.getWriter().println(gson.toJson(new ApiResponse<>(500, "Error while deleting data!", e)));
        }
    }
}
