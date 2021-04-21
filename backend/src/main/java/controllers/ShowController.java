package controllers;

import io.javalin.http.Context;
import models.Login;
import models.Register;
import org.json.JSONObject;

public class ShowController {

    static public void addShow(Context ctx) {

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

        ctx.result("Hello");

    }

}
