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

    static public void getPendingShows(Context ctx) {

        JSONObject payload = new JSONObject(ctx.body());
        JSONObject resp = new JSONObject();

        if (payload.has("token")) {

            String token = (String) payload.get("token");
            JSONObject preExecutionResponse = AdminService.beforeExecutionCheck(token);

            if (!preExecutionResponse.getBoolean("success")) {

                resp = preExecutionResponse;

            } else {

                resp = AdminService.getPendingShows();

            }

        } else {

            resp.put("success", false);
            resp.put("message", "Please send a token parameter.");

        }

        ctx.result(resp.toString());

    }

    static public void approvePendingShow(Context ctx) {

        JSONObject payload = new JSONObject(ctx.body());
        JSONObject resp = new JSONObject();

        if (payload.has("token")) {

            String token = (String) payload.get("token");
            JSONObject preExecutionResponse = AdminService.beforeExecutionCheck(token);

            if (!preExecutionResponse.getBoolean("success")) {

                resp = preExecutionResponse;

            } else if (!payload.has("show_id")) {

                resp.put("success", false);
                resp.put("message", "You need to input a show ID.");

            } else {

                Integer showId = (Integer) payload.get("show_id");

                resp = AdminService.changeShowStatus(showId, 1);

            }

        }

        ctx.result(resp.toString());

    }

    static public void rejectPendingShow(Context ctx) {

        JSONObject payload = new JSONObject(ctx.body());
        JSONObject resp = new JSONObject();

        if (payload.has("token")) {

            String token = (String) payload.get("token");
            JSONObject preExecutionResponse = AdminService.beforeExecutionCheck(token);

            if (!preExecutionResponse.getBoolean("success")) {

                resp = preExecutionResponse;

            } else if (!payload.has("show_id")) {

                resp.put("success", false);
                resp.put("message", "You need to input a show ID.");

            } else {

                Integer showId = (Integer) payload.get("show_id");

                resp = AdminService.changeShowStatus(showId, 2);

            }

        }

        ctx.result(resp.toString());

    }

    static public void deleteShow(Context ctx) {

        JSONObject payload = new JSONObject(ctx.body());
        JSONObject resp = new JSONObject();

        if (payload.has("token")) {

            String token = (String) payload.get("token");
            JSONObject preExecutionResponse = AdminService.beforeExecutionCheck(token);

            if (!preExecutionResponse.getBoolean("success")) {

                resp = preExecutionResponse;

            } else if (!payload.has("show_id")) {

                resp.put("success", false);
                resp.put("message", "You need to input a show ID.");

            } else {

                Integer showId = (Integer) payload.get("show_id");

                resp = AdminService.deleteShow(showId);

            }

        }

        ctx.result(resp.toString());

    }


    static public void editShow(Context ctx) {

        JSONObject payload = new JSONObject(ctx.body());
        JSONObject resp = new JSONObject();

        if (payload.has("token")) {

            String token = (String) payload.get("token");
            JSONObject preExecutionResponse = AdminService.beforeExecutionCheck(token);

            if (!preExecutionResponse.getBoolean("success")) {

                resp = preExecutionResponse;

            } else if (!payload.has("show_id")) {

                resp.put("success", false);
                resp.put("message", "You need to input a show ID.");

            } else {

                Integer showId = (Integer) payload.get("show_id");
                String title = (String) payload.get("title");
                String genre = (String) payload.get("genre");
                double length = Double.parseDouble((String) payload.get("length"));
                String type = (String) payload.get("type");
                int year = Integer.parseInt((String) payload.get("year"));
                int procoId = Integer.parseInt((String) payload.get("proco_id"));

                // Status must be either 1 or 2

                int status = Integer.parseInt((String) payload.get("status"));

                resp = AdminService.editShow(showId, title, genre, length, type, year, procoId, status);

            }

        }

        ctx.result(resp.toString());

    }

}
