package controllers;

import io.javalin.http.Context;
import services.AdminService;
import org.json.JSONObject;

public class AdminController {

    static public void rejectAccountRequest(Context ctx) {

        JSONObject payload = new JSONObject(ctx.body());
        JSONObject resp = new JSONObject();

        if (payload.has("token")) {

            String token = (String) payload.get("token");
            JSONObject preExecutionResponse = AdminService.beforeExecutionCheck(token);

            if (!preExecutionResponse.getBoolean("success")) {

                resp = preExecutionResponse;

            } else if (!payload.has("username")) {

                resp.put("success", false);
                resp.put("message", "You need to input a username.");

            } else {

                String username = (String) payload.get("username");

                resp = AdminService.changeUserStatus(username, 2);

            }

        }

        ctx.result(resp.toString());

    }

    static public void approveAccountRequest(Context ctx) {

        JSONObject payload = new JSONObject(ctx.body());
        JSONObject resp = new JSONObject();

        if (payload.has("token")) {

            String token = (String) payload.get("token");
            JSONObject preExecutionResponse = AdminService.beforeExecutionCheck(token);

            if (!preExecutionResponse.getBoolean("success")) {

                resp = preExecutionResponse;

            } else if (!payload.has("username")) {

                resp.put("success", false);
                resp.put("message", "You need to input a username.");

            } else {

                String username = (String) payload.get("username");

                resp = AdminService.changeUserStatus(username, 1);

            }

        }

        ctx.result(resp.toString());

    }

    static public void getAccountRequests(Context ctx) {

        JSONObject payload = new JSONObject(ctx.body());
        JSONObject resp = new JSONObject();

        if (payload.has("token")) {

            String token = (String) payload.get("token");
            JSONObject preExecutionResponse = AdminService.beforeExecutionCheck(token);

            if (!preExecutionResponse.getBoolean("success")) {

                resp = preExecutionResponse;

            } else {

                resp = AdminService.getAccountRequests();

            }

        } else {

            resp.put("success", false);
            resp.put("message", "Please send a token parameter.");

        }

        ctx.result(resp.toString());

    }

}
