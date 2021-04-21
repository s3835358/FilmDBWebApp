package controllers;

import io.javalin.http.Context;
import services.InformationService;
import org.json.JSONObject;

public class InformationController {

    static public void getAllPcos(Context ctx) {

        JSONObject resp = InformationService.getAllPCOs();

        ctx.result(resp.toString());

    }

    static public void getAllGenres(Context ctx) {

        JSONObject resp = InformationService.getALlGenres();

        ctx.result(resp.toString());

    }

}