package controllers;

import dao.ProductionCompanyDAO;
import io.javalin.http.Context;
import models.ProductionCompany;
import org.json.JSONObject;

import java.util.ArrayList;

public class InformationController {

    static public void getAllPcos(Context ctx) {

        ArrayList<ProductionCompany> productionCompanies = ProductionCompanyDAO.getAll();
        JSONObject resp = new JSONObject();
        Integer k = 0;

        for (ProductionCompany i : productionCompanies) {

            JSONObject pco = new JSONObject();

            pco.put("id", i.getId());
            pco.put("name", i.getName());

            resp.put(String.valueOf(k), pco);

            k++;

        }

        ctx.result(resp.toString());

    }

}
