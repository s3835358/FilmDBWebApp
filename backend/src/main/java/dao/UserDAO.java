package dao;

import models.ProductionCompany;
import models.User;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

    public static boolean add(User user) {

        Connection con = DBConnection.createConnection();

        try {

            // Create a MySQL prepared statement

            PreparedStatement statement = con.prepareStatement("INSERT INTO account (username, password, email, user_type, country, gender, first_name, last_name, birth_year, zip_code, phone_number, token) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            // Plug in the parameters

            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPasswordHash());
            statement.setString(3, user.getEmail());
            statement.setInt(4, user.getUserType());
            statement.setString(5, user.getCountry());
            statement.setString(6, user.getGender());
            statement.setString(7, user.getFirstName());
            statement.setString(8, user.getLastName());
            statement.setInt(9, user.getBirthYear());
            statement.setString(10, user.getZipCode());
            statement.setInt(11, user.getPhoneNumber());
            statement.setString(12, user.getToken());

            return true;

        } catch (Exception e) {

            System.out.println("PANIC: Error inserting user");
            System.out.println("ERROR: " + e.getMessage());

        }

        DBConnection.closeConnection(con);

        return false;

    }

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

            System.out.println("PANIC: Error updating user");
            System.out.println("ERROR: " + e.getMessage());

        }

        DBConnection.closeConnection(con);

    }

    public static User get(String target) {

        Connection con = DBConnection.createConnection();
        User user = new User();

        try {

            PreparedStatement statement = con.prepareStatement("SELECT * FROM account WHERE username = ? OR email = ?");
            statement.setString(1, target);
            statement.setString(2, target);
            ResultSet res = statement.executeQuery();

            if (res.next()) {

                user.setUsername(res.getString("username"));
                user.setFirstName(res.getString("first_name"));
                user.setLastName(res.getString("last_name"));
                user.setPasswordHash(res.getString("password"));
                user.setUserType(res.getInt("user_type"));
                user.setPhoneNumber(res.getInt("phone_number"));
                user.setBirthYear(res.getInt("birth_year"));
                user.setToken(res.getString("token"));
                user.setCountry(res.getString("country"));
                user.setGender(res.getString("gender"));
                user.setZipCode(res.getString("zipCode"));

            }

        } catch (Exception e) {

            System.out.println("PANIC: Failed to get production company");
            System.out.println("ERROR: " + e.getMessage());

        }

        return user;

    }

}
