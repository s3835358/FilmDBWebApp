package controllers;

import io.javalin.http.Context;
import org.json.JSONObject;
import dao.LoginDAO;

public class AccountController {

    static public void login(Context ctx) {

        String body = ctx.body();

        JSONObject payload = new JSONObject(body);

        String username = (String) payload.get("username");
        String password = (String) payload.get("password");

        String token = LoginDAO.login(username, password);

        JSONObject resp = new JSONObject();


        if (token != null) {

            resp.put("success", true);
            resp.put("message", "You have been successfully logged in!");

        } else {

            resp.put("success", false);
            resp.put("message", "It seems you have put incorrect login details. Please try again!");

        }

        resp.put("token", token);

        ctx.html(resp.toString());

    }

    ;

}
