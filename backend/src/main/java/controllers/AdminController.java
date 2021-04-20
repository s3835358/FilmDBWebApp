package controllers;

import dao.AccountRequestDAO;
import io.javalin.http.Context;
import models.AccountRequest;
import org.json.JSONObject;
import util.Common;

import java.util.ArrayList;

public class AdminController {

    static public void rejectAccountRequest(Context ctx) {

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

            } else if (!payload.has("username")) {

                resp.put("success", false);
                resp.put("message", "You need to input a username.");

            } else {

                String username = (String) payload.get("username");
                AccountRequest accountRequest = AccountRequestDAO.get(username);

                if (accountRequest.getUsername() == null) {

                    resp.put("success", false);
                    resp.put("message", "The account request does not exist!");

                } else {

                    accountRequest.setUserApproved(2);

                    AccountRequestDAO.update(accountRequest);

                    resp.put("success", true);
                    resp.put("message", "The account status has been updated!");

                }

            }

        }

        ctx.result(resp.toString());

    }

    static public void approveAccountRequest(Context ctx) {

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

            } else if (!payload.has("username")) {

                resp.put("success", false);
                resp.put("message", "You need to input a username.");

            } else {

                String username = (String) payload.get("username");
                AccountRequest accountRequest = AccountRequestDAO.get(username);

                if (accountRequest.getUsername() == null) {

                    resp.put("success", false);
                    resp.put("message", "The account request does not exist!");

                } else {

                    accountRequest.setUserApproved(1);

                    AccountRequestDAO.update(accountRequest);

                    resp.put("success", true);
                    resp.put("message", "The account status has been updated!");

                }

            }

        }

        ctx.result(resp.toString());

    }

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
                    request.put("phone_number", i.getPhoneNumber());

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
