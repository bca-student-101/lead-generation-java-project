import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "UpdateContentServlet", urlPatterns = {"/UpdateContentServlet"})
public class UpdateContentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("view-leads.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String newHeading = request.getParameter("new_heading");
        String newText = request.getParameter("new_text");
        String newImage = request.getParameter("new_image");

        String dbUrl = "jdbc:mysql://altaria.proxy.rlwy.net:26849/railway?useSSL=false&allowPublicKeyRetrieval=true";
        String dbUser = "root";
        String dbPass = "FGRxPAsATZIDZCCVYmZZvPyrAljtiCgt";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);

            String query1 = "UPDATE website_content SET content_text=? WHERE section_name='main_heading'";
            PreparedStatement ps1 = conn.prepareStatement(query1);
            ps1.setString(1, newHeading);
            ps1.executeUpdate();
            ps1.close();

            String query2 = "UPDATE website_content SET content_text=? WHERE section_name='main_text'";
            PreparedStatement ps2 = conn.prepareStatement(query2);
            ps2.setString(1, newText);
            ps2.executeUpdate();
            ps2.close();

            String query3 = "UPDATE website_content SET content_text=? WHERE section_name='hero_img'";
            PreparedStatement ps3 = conn.prepareStatement(query3);
            ps3.setString(1, newImage);
            ps3.executeUpdate();
            ps3.close();

            conn.close();

            response.sendRedirect("view-leads.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("view-leads.jsp");
        }
    }
}
