package controllers;

import io.javalin.http.Context;
import org.json.JSONObject;
import dao.LoginDAO;

public class AccountController {

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
