package controllers;

import io.javalin.http.Context;
import org.json.JSONObject;
import util.*;

public class AccountController {

    static public void login(Context ctx) {

        String body = ctx.body();

        JSONObject payload  = new JSONObject(body);

        String email = (String) payload.get("email");
        String password = (String) payload.get("password");

        ctx.html(email + "_" + password);

    };

}
