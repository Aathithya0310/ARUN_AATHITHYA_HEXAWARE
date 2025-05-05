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

