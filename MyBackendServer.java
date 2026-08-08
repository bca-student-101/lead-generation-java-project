
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class MyBackendServer {

    public static void main(String[] args) throws IOException {
int port = Integer.parseInt(
        System.getenv().getOrDefault("PORT", "8080")
);

HttpServer server = HttpServer.create(
        new InetSocketAddress("0.0.0.0", port), 0);
       // HttpServer server = HttpServer.create(
               // new InetSocketAddress(8080), 0);

        server.createContext("/api/data", new MyHandler());

        server.setExecutor(null);

        System.out.println("Server running on port 8080...");

        server.start();
    }

    static class MyHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange exchange) throws IOException {

            exchange.getResponseHeaders()
                    .add("Access-Control-Allow-Origin", "*");

            exchange.getResponseHeaders()
                    .add("Access-Control-Allow-Methods", "GET, POST, OPTIONS");

            exchange.getResponseHeaders()
                    .add("Access-Control-Allow-Headers", "Content-Type");

            if (exchange.getRequestMethod().equals("POST")) {

                InputStream input = exchange.getRequestBody();

                String data = new String(input.readAllBytes());

                System.out.println("Received Data:");
                System.out.println(data);

                try {

                    // JSON se value nikalna
                    String name = data.split("\"name\":\"")[1].split("\"")[0];
                    String email = data.split("\"email\":\"")[1].split("\"")[0];
                    String phone = data.split("\"phone\":\"")[1].split("\"")[0];
                    String company = data.split("\"company\":\"")[1].split("\"")[0];
                    String requirement = data.split("\"requirement\":\"")[1].split("\"")[0];
                    String message = data.split("\"message\":\"")[1].split("\"")[0];

                    Connection con = DatabaseConnection.getConnection();

                    String sql = "INSERT INTO consultation(name,email,phone,company,requirement,message) VALUES(?,?,?,?,?,?)";

                    PreparedStatement ps = con.prepareStatement(sql);

                    ps.setString(1, name);
                    ps.setString(2, email);
                    ps.setString(3, phone);
                    ps.setString(4, company);
                    ps.setString(5, requirement);
                    ps.setString(6, message);

                    ps.executeUpdate();

                    System.out.println("Data saved in MySQL");

                    con.close();

                } catch (Exception e) {

                    e.printStackTrace();

                }

                String response = "{\"message\":\"Data saved successfully\"}";

                exchange.sendResponseHeaders(
                        200,
                        response.getBytes().length);

                OutputStream os = exchange.getResponseBody();

                os.write(response.getBytes());

                os.close();

            }

            else {

                String response = "{\"message\":\"Backend is running\"}";

                exchange.sendResponseHeaders(
                        200,
                        response.getBytes().length);

                OutputStream os = exchange.getResponseBody();

                os.write(response.getBytes());

                os.close();

            }

        }
    }
}