/*package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DBPropertyUtil {

    public static String getPropertyString(String fileName) {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(fileName)) {
            props.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        String host = props.getProperty("localhost");
        String port = props.getProperty("3306");
        String dbName = props.getProperty("crime_db");
        String user = props.getProperty("root");
        String password = props.getProperty("arun");

        return "jdbc:mysql://" + host + ":" + port + "/" + dbName + "?user=" + user + "&password=" + password;
    }
}
*/
/*package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DBPropertyUtil {

    public static Properties getPropertyString(String fileName) {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(fileName)) {
            props.load(fis);
        } catch (IOException e) {
            System.out.println("Error reading property file: " + e.getMessage());
        }
        return props;
    }
}
*/


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