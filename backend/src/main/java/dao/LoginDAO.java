package dao;

import java.security.SecureRandom;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Base64;

import at.favre.lib.crypto.bcrypt.BCrypt;
import models.User;
import util.*;

public class LoginDAO {

    public static String login(String username, String password) {

        Connection con = DBConnection.createConnection();
        String token;

        try {

            token = null;

            // Load the user with their username

            PreparedStatement statement = con.prepareStatement("SELECT password, email, country, gender, first_name, last_name FROM account WHERE username = ?");
            statement.setString(1, username);
            ResultSet res = statement.executeQuery();

            // If the user row exists

            if (res.next()) {

                // Load the information from the row

                String passwordHash = res.getString("password");
                String email = res.getString("email");
                String country = res.getString("country");
                String gender = res.getString("gender");
                String firstName = res.getString("first_name");
                String lastName = res.getString("last_name");

                // Check if the password is correct using BCrypt algorithm

                BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), passwordHash);

                // If the password matches

                if(result.verified) {

                    // Get random entropy

                    SecureRandom random = new SecureRandom();
                    Base64.Encoder base64Encoder = Base64.getUrlEncoder();

                    byte[] bytes = new byte[64];
                    random.nextBytes(bytes);

                    // Encode the random bytes into a Base64 string

                    token = base64Encoder.encodeToString(bytes);

                    // Create the User model

                    User user = new User();

                    // Populate the model

                    user.setUsername(username);
                    user.setPasswordHash(passwordHash);
                    user.setEmail(email);
                    user.setCountry(country);
                    user.setGender(gender);
                    user.setFirstName(firstName);
                    user.setLastName(lastName);
                    user.setToken(token);

                    // Send it to User DAO to update (because we are updating the token)

                    UserDAO.update(user);

                }

            }

        } catch (Exception e) {

            token = null;

            System.out.println("PANIC: Failed to get user");
            System.out.println("ERROR: " + e.getMessage());

        }

        DBConnection.closeConnection(con);

        return token;

    }

}
