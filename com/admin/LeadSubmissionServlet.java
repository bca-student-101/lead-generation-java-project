package com.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LeadSubmissionServlet")
public class LeadSubmissionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("index.html");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String company = request.getParameter("company");
        String requirement = request.getParameter("requirement");
        String message = request.getParameter("message");

        String dbUrl = "jdbc:mysql://altaria.proxy.rlwy.net:26849/railway?useSSL=false&allowPublicKeyRetrieval=true";
        String dbUser = "root";
        String dbPass = "FGRxPAsATZIDZCCVYmZZvPyrAljtiCgt";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass);

            String query = "INSERT INTO leads (name, email, phone, company, requirement, message) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.setString(4, company);
            ps.setString(5, requirement);
            ps.setString(6, message);

            ps.executeUpdate();

            out.println("<script type='text/javascript'>");
            out.println("alert('Lead Successfully Submitted!');");
            out.println("window.location.href='index.html';");
            out.println("</script>");

            con.close();
        } catch (Exception e) {
            out.println("Database Error: " + e.getMessage());
        }
    }
}
