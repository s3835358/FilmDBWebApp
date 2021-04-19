package controllers;

import dao.AccountRequestDAO;
import dao.ProductionCompanyDAO;
import io.javalin.http.Context;
import models.AccountRequest;
import models.ProductionCompany;
import org.json.JSONObject;
import util.Common;

import java.util.ArrayList;

public class AdminController {

    static public void getAccountRequests(Context ctx) {

        JSONObject payload = new JSONObject(ctx.body());
        JSONObject resp = new JSONObject();

        if (payload.has("token")) {

            String token = (String) payload.get("token");

            if (!Common.isLoggedIn(token)) {

                resp.put("success", false);
                resp.put("message", "Your session has expired. Please try logging in again.");

            } else if (!Common.isAdmin(token)) {

                resp.put("success", false);
                resp.put("message", "You need to be an administrator to access this endpoint.");

            } else {

                ArrayList<AccountRequest> accountRequests = AccountRequestDAO.getAll();
                int k = 0;

                for (AccountRequest i : accountRequests) {

                    JSONObject request = new JSONObject();

                    request.put("name", i.getName());
                    request.put("username", i.getUsername());
                    request.put("email", i.getEmail());
                    request.put("user_type", i.getEmail());
                    request.put("proco_name", i.getProcoName());
                    request.put("phone_number", i.getPhone_number());

                    resp.put(String.valueOf(k), request);

                    k++;

                }

            }

        } else {

            resp.put("success", false);
            resp.put("message", "Please send a token parameter.");

        }

        ctx.result(resp.toString());

    }

}
