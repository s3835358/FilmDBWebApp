package dao;

import models.User;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UserDAO {

    public static void update(User user) {

        Connection con = DBConnection.createConnection();

        try {

            // Create a MySQL prepared statement

            PreparedStatement statement = con.prepareStatement("UPDATE account SET password = ?, email = ?, country = ?, gender = ?, first_name = ?, last_name = ?, token = ? WHERE username = ?");

            // Plug in the parameters

            statement.setString(1, user.getPasswordHash());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getCountry());
            statement.setString(4, user.getGender());
            statement.setString(5, user.getFirstName());
            statement.setString(6, user.getLastName());
            statement.setString(7, user.getToken());
            statement.setString(8, user.getUsername());

            // Update the row

            statement.executeUpdate();

        } catch (Exception e) {

            System.out.println("PANIC: Error inserting user");
            System.out.println("ERROR: " + e.getMessage());

        }

        DBConnection.closeConnection(con);

    }

}
