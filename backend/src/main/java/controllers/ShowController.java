package controllers;

import io.javalin.http.Context;
import org.json.JSONObject;
import services.ShowService;
import util.Common;

public class ShowController {

    static public void addShow(Context ctx) {

        JSONObject payload = new JSONObject(ctx.body());
        JSONObject resp = new JSONObject();

        if (payload.has("token")) {

            String token = (String) payload.get("token");
            String title = (String) payload.get("title");
            int genre = Integer.parseInt((String) payload.get("genre"));
            double length = Double.parseDouble((String) payload.get("length"));
            String type = (String) payload.get("type");
            int year = Integer.parseInt((String) payload.get("year"));
            int procoId = Integer.parseInt((String) payload.get("proco_id"));

            JSONObject userLoggedInCheck = Common.userLoggedInCheck(token);

            if (!userLoggedInCheck.getBoolean("success")) {

                resp = userLoggedInCheck;

            } else {

                resp = ShowService.add(title, genre, length, type, year, procoId, Common.getUserType(token));

            }

        }

        ctx.result(resp.toString());

    }

}
