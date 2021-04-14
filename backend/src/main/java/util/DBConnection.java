package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection createConnection() {

        Connection con = null;

        // MySQL URL with connection properties

        String url = "jdbc:mysql://" + Config.DATABASE_HOST + ":" + Config.DATABASE_PORT + "/" + Config.DATABASE_NAME + "?useUnicode=true&characterEncoding=utf8"; //MySQL URL and followed by the database name

        try {

            // Load the appropriate MySQL driver

            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            // Attempt to create a MySQL server connection

            con = DriverManager.getConnection(url, Config.DATABASE_USERNAME, Config.DATABASE_PASSWORD);

        } catch (Exception e) {

            System.out.println("PANIC: Failed to create a database connection");
            System.out.println("ERROR: " + e.getMessage());

        }

        return con;

    }

    public static void closeConnection(Connection con) {

        // Try closing the connection, otherwise print the errors on the console

        try {

            con.close();

        } catch (Exception e) {

            System.out.println("PANIC: Error closing connection");
            System.out.println("ERROR: " + e.getMessage());

        }

    }

}