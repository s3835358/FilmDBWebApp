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
            System.out.println("Printing connection object " + con);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return con;

    }

}