package com.admin;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String user = request.getParameter("username");
        String pass = request.getParameter("password");
        
        String dbUrl = "jdbc:mysql://altaria.proxy.rlwy.net:26849/railway?useSSL=false&allowPublicKeyRetrieval=true";
        //String dbUrl = "jdbc:mysql://mysql.railway.internal:3306/railway";
        String dbUser = "root"; 
        String dbPass = "FGRxPAsATZIDZCCVYmZZvPyrAljtiCgt";
         
        //connection con =null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
           Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            
            String query = "SELECT * FROM admin_user WHERE username=? AND password=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, pass);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()) {
                HttpSession session = request.getSession();
                session.setAttribute("adminUser", user);
                response.sendRedirect("view-leads.jsp"); 
            } else {
                out.println("<script type='text/javascript'>");
                out.println("alert('गलत यूजरनेम या पासवर्ड!');");
                out.println("window.location.href='admin.html';");
                out.println("<script>");
            }
            con.close();
        } catch(Exception e) {
            out.println("डेटाबेस कनेक्शन एरर: " + e.getMessage());
        }
    }
}
