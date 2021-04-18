package util;

import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Config {

    // Declare configuration parameters

    public static int APPLICATION_PORT;
    public static String DATABASE_HOST;
    public static String DATABASE_NAME;
    public static String DATABASE_USERNAME;
    public static String DATABASE_PASSWORD;
    public static int DATABASE_PORT;

    public static void loadConfig() {

        try {

            String configContents = Files.readString(Paths.get("config.json"));
            JSONObject obj = new JSONObject(configContents);

            // Load the configuration parameters

            APPLICATION_PORT = obj.getInt("application_port");
            DATABASE_HOST = obj.getString("database_host");
            DATABASE_NAME = obj.getString("database_name");
            DATABASE_USERNAME = obj.getString("database_username");
            DATABASE_PASSWORD = obj.getString("database_password");
            DATABASE_PORT = obj.getInt("database_port");

        } catch (Exception e) {

            System.out.println("PANIC: Error reading the configuration file");
            System.out.println("ERROR: " + e.getMessage());

        }

    }

}