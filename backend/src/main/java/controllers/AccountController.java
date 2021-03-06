package controllers;

import io.javalin.http.Context;
import services.LoginService;
import services.RegisterService;
import org.json.JSONObject;

public class AccountController {

    static public void register(Context ctx) {

        JSONObject payload = new JSONObject(ctx.body());

        String username = (String) payload.get("username");
        String firstName = (String) payload.get("first_name");
        String lastName = (String) payload.get("last_name");
        String password = (String) payload.get("password");
        String email = (String) payload.get("email");
        String country = (String) payload.get("country");
        String gender = (String) payload.get("gender");
        String zipCode = (String) payload.get("zip_code");
        Integer birthYear = Integer.parseInt((String) payload.get("birth_year"));
        int userType = Integer.parseInt((String) payload.get("user_type"));

        String phoneNumber = "";
        int productionCompanyId = 0;

        // If userType == 2

        if(userType == 2 && payload.has("production_company")) {
            productionCompanyId = Integer.parseInt((String) payload.get("production_company"));
        }

        // If userType == 2 or 3

        if(userType > 1 && userType < 4 && payload.has("phone_number")) {
            phoneNumber = (String) payload.get("phone_number");
        }

        // Create Register model

        RegisterService register = new RegisterService(username, password, email, country, gender, firstName, lastName, zipCode, birthYear, userType, phoneNumber, productionCompanyId);

        // Create a response JSON object

        JSONObject resp = register.validate();

        // Do some checks

        if (((Boolean) resp.get("success"))) {

            Boolean accountCreated = register.createAccount();

            if (accountCreated) {

                resp.put("success", true);
                resp.put("message", "Your account has been successfully created! You may login now.");

            } else {

                resp.put("success", false);
                resp.put("message", "We were unable to create an account due to an unknown reason.");

            }

        }

        ctx.result(resp.toString());

    }

    static public void login(Context ctx) {

        JSONObject payload = new JSONObject(ctx.body());

        // Get username, password from JSON decoded body

        String username = (String) payload.get("username");
        String password = (String) payload.get("password");

        // Attempt to authenticate using LoginDAO

        LoginService login = new LoginService(username, password);

        String token = login.authenticate();

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

        ctx.result(resp.toString());

    }

}
