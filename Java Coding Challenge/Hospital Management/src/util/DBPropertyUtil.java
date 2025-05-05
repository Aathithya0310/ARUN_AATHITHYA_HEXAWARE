package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DBPropertyUtil {
    public static String getPropertyString(String propertyFileName) throws IOException{
        Properties properties = new Properties();
        FileInputStream input = new FileInputStream(propertyFileName);

        try {
            properties.load(input);
            String hostname = properties.getProperty("db.hostname");
            String dbname = properties.getProperty("db.name");
            String username = properties.getProperty("db.username");
            String password = properties.getProperty("db.password");
            String port = properties.getProperty("db.port");

            return String.format("jdbc:mysql://%s:%s/%s?user=%s&password=%s",
                    hostname, port, dbname, username, password);
        } finally {
            input.close();
        }
    }
}