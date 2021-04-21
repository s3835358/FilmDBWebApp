package util;

import models.ProductionCompany;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.*;

public class Common {

    public static int getUserType(String token) {

        Connection con = DBConnection.createConnection();

        try {

            PreparedStatement statement = con.prepareStatement("SELECT user_type FROM account WHERE token = ?");
            statement.setString(1, token);
            ResultSet res = statement.executeQuery();

            if (res.next()) {

                return res.getInt("user_type");

            }

        } catch (Exception e) {

            System.out.println("PANIC: Failed to get user type status");
            System.out.println("ERROR: " + e.getMessage());

        }

        return 0;

    }

    public static JSONObject userLoggedInCheck(String token) {

        JSONObject output = new JSONObject();

        if (!Common.isLoggedIn(token)) {

            output.put("success", false);
            output.put("message", "Your session has expired. Please try logging in again.");

        } else {

            output.put("success", true);

        }

        return output;

    }

    public static boolean isAdmin(String token) {

        Connection con = DBConnection.createConnection();

        try {

            PreparedStatement statement = con.prepareStatement("SELECT user_type FROM account WHERE token = ?");
            statement.setString(1, token);
            ResultSet res = statement.executeQuery();

            if (res.next()) {

                int userType = res.getInt("user_type");

                if (userType == 4) {
                    return true;
                }

            }

        } catch (Exception e) {

            System.out.println("PANIC: Failed to get user type status");
            System.out.println("ERROR: " + e.getMessage());

        }

        return false;

    }

    public static boolean isLoggedIn(String token) {

        Connection con = DBConnection.createConnection();

        try {

            PreparedStatement statement = con.prepareStatement("SELECT username FROM account WHERE token = ?");
            statement.setString(1, token);
            ResultSet res = statement.executeQuery();

            if (res.next()) {
                return true;
            }

        } catch (Exception e) {

            System.out.println("PANIC: Failed to get user login status");
            System.out.println("ERROR: " + e.getMessage());

        }

        return false;

    }

    public static boolean isUsernameValid(String username) {

        String regex = "^[A-Za-z]\\w{2,29}$";
        Pattern p = Pattern.compile(regex);

        if (username == null || username.isEmpty()) {
            return false;
        }

        Matcher m = p.matcher(username);

        return m.matches();

    }

    public static boolean isEmailValid(String email) {

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);

        if (email == null || email.isEmpty()) {
            return false;
        }

        return pat.matcher(email).matches();

    }

}
