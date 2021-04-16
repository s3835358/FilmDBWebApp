package controllers;

import io.javalin.http.Context;
import org.json.JSONObject;
import dao.LoginDAO;
import util.Common;

public class AccountController {

    static public void register(Context ctx) {

        JSONObject payload = new JSONObject(ctx.body());

        // Get username, password from JSON decoded body

        String username = (String) payload.get("username");
        String firstName = (String) payload.get("first_name");
        String lastName = (String) payload.get("last_name");
        String password = (String) payload.get("password");
        String email = (String) payload.get("email");
        String country = (String) payload.get("country");
        String gender = (String) payload.get("gender");
        String zip_code = (String) payload.get("zip_code");
        String birth_year = (String) payload.get("birth_year");
        String user_type = (String) payload.get("user_type");

        // Create a response JSON object

        JSONObject resp = new JSONObject();

        Boolean errorEncountered = false;

        // Do some checks

        if (Common.isUsernameValid(username)) {

            resp.put("success", true);
            resp.put("message", "You have been successfully logged in!");

            errorEncountered = true;

        }

        if (!errorEncountered) {

            // Attempt to authenticate using RegisterDAO

            Boolean accountCreated = RegisterDAO.register(username, firstName, lastName, password, email, country, gender, zip_code, birth_year, user_type);

            // Populate it appropriately

            if (accountCreated) {

                resp.put("success", true);
                resp.put("message", "You have been successfully logged in!");

            } else {

                resp.put("success", false);
                resp.put("message", "We were unable to create an account due to an unknown reason.");

            }

        }

        ctx.html(resp.toString());

    }

    static public void login(Context ctx) {

        JSONObject payload = new JSONObject(ctx.body());

        // Get username, password from JSON decoded body

        String username = (String) payload.get("username");
        String password = (String) payload.get("password");

        // Attempt to authenticate using LoginDAO

        String token = LoginDAO.login(username, password);

        // Create a response JSON object

        JSONObject resp = new JSONObject();

        // Populate it appropriately

        if (token != null) {

            resp.put("success", true);
            resp.put("message", "You have been successfully logged in!");

        } else {

            resp.put("success", false);
            resp.put("message", "It seems you have used incorrect login details. Please try again!");

        }

        resp.put("token", token);

        ctx.html(resp.toString());

    }

}
