/*package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnUtil {
    private static Connection conn;

    public static Connection getConnection() {
        if (conn == null) {
            try {
                String connectionString = DBPropertyUtil.getPropertyString("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(connectionString);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return conn;
    }
}



package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnUtil {

    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Properties props = DBPropertyUtil.getPropertyString("db.properties");
               // jdbc:mysql://127.0.0.1:3306/crime_db//
                String url = props.getProperty("jdbc:mysql://localhost:3306/crime_db");
                String user = props.getProperty("root");
                String password = props.getProperty("arun");

                connection = DriverManager.getConnection(url, user, password);
                System.out.println("Database connection successful!");
            } catch (SQLException e) {
                System.out.println("Database connection failed: " + e.getMessage());
            }
        }
        return connection;
    }
}

 */
package util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnUtil {
    public static Connection getConnection() throws SQLException, IOException {
        String connectionString = DBPropertyUtil.getPropertyString("db.properties");
        return DriverManager.getConnection(connectionString);
    }
}
