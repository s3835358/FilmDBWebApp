package models;

import dao.ProductionCompanyDAO;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Information {

    public static JSONObject getAllPCOs() {

        ArrayList<ProductionCompany> productionCompanies = ProductionCompanyDAO.getAll();
        JSONObject output = new JSONObject();
        JSONArray pcos = new JSONArray();
        int k = 0;

        for (ProductionCompany i : productionCompanies) {

            JSONObject pco = new JSONObject();

            pco.put("id", i.getId());
            pco.put("name", i.getName());

            pcos.put(k, pco);

            k++;

        }

        output.put("success", true);
        output.put("pcos", pcos);

        return output;

    }

}