package controllers;

import io.javalin.http.Context;
import models.Information;
import org.json.JSONObject;

public class InformationController {

    static public void getAllPcos(Context ctx) {

        JSONObject resp = Information.getAllPCOs();

        ctx.result(resp.toString());

    }

}