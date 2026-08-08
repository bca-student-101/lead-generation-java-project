
import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    public static Connection getConnection() throws Exception {

        // MySQL driver load
        Class.forName("com.mysql.cj.jdbc.Driver");

        String url = "jdbc:mysql://localhost:3306/landing_page_db";
        String username = "root";
        String password = "manisha@01";

        Connection con = DriverManager.getConnection(
                url,
                username,
                password);

        return con;
    }
}

// import java.sql.Connection;
// import java.sql.DriverManager;

// public class DatabaseConnection {

// public static Connection getConnection() throws Exception {

// // MySQL driver
// Class.forName("com.mysql.cj.jdbc.Driver");

// String host = System.getenv("MYSQLHOST");
// String port = System.getenv("MYSQLPORT");
// String database = System.getenv("MYSQLDATABASE");
// String username = System.getenv("MYSQLUSER");
// String password = System.getenv("MYSQLPASSWORD");

// String url = "jdbc:mysql://" + host + ":" + port + "/" + database;

// Connection con = DriverManager.getConnection(
// url,
// username,
// password);

// return con;
// }
// }