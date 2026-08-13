
<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Live Leads Dashboard</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 30px; background-color: #f4f7f6; }
        h2 { color: #333; text-align: center; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; background: #fff; }
        th, td { border: 1px solid #ddd; padding: 12px; text-align: left; }
        th { background-color: #4CAF50; color: white; }
        tr:nth-child(even) { background-color: #f2f2f2; }
    </style>
</head>
<body>

    <h2>📊 Live Leads Dashboard</h2>
    
    <table>
        <tr>
            <th>Name</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Company</th>
            <th>Requirement</th>
            <th>Message</th>
        </tr>
        
        <%
        try {
            String dbUrl = "jdbc:mysql://altaria.proxy.rlwy.net:26849/railway?useSSL=false&allowPublicKeyRetrieval=true";
            String dbUser = "root";
            String dbPass = "FGRxPAsATZIDZCCVYmZZvPyrAljtiCgt";
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            
            // Yahan hum leads table se saara data nikal rahe hain
            String query = "SELECT * FROM leads";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
        %>
        <tr>
            <td><%= rs.getString("name") %></td>
            <td><%= rs.getString("email") %></td>
            <td><%= rs.getString("phone") %></td>
            <td><%= rs.getString("company") %></td>
            <td><%= rs.getString("requirement") %></td>
            <td><%= rs.getString("message") %></td>
        </tr>
        <%
            }
            con.close();
        } catch(Exception e) {
            out.println("<p style='color:red;'>Data load karne mein error: " + e.getMessage() + "</p>");
        }
        %>
    </table>

    <br><hr><br>
<div style="margin: 20px 0; padding: 20px; border: 1px solid #ddd; background: #fdfdfd; border-radius: 8px;">
    <h3 style="color: #333;">Update Website Content (Dynamic CMS)</h3>
    <form action="UpdateContentServlet" method="POST">
        <div style="margin-bottom: 10px;">
            <label>New Heading:</label><br>
            <input type="text" name="new_heading" placeholder="Enter new heading..." required style="width: 80%; padding: 6px;">
        </div>
        <div style="margin-bottom: 10px;">
            <label>New Text:</label><br>
            <textarea name="new_text" placeholder="Enter new description..." required style="width: 80%; padding: 6px; height: 60px;"></textarea>
        </div>
        <div style="margin-bottom: 15px;">
            <label>New Image URL:</label><br>
            <input type="url" name="new_image" placeholder="Paste new image online link..." required style="width: 80%; padding: 6px;">
        </div>
        <button type="submit" style="background: #28a745; color: white; padding: 8px 16px; border: none; border-radius: 4px; cursor: pointer;">Apply Changes Live</button>
    </form>
</div>
</body>
</html>