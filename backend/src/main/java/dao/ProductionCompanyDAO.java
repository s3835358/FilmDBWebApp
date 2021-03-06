package dao;

import models.ProductionCompany;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProductionCompanyDAO {

    public static void update(ProductionCompany productionCompany) {

        Connection con = DBConnection.createConnection();

        try {

            // Create a MySQL prepared statement

            PreparedStatement statement = con.prepareStatement("UPDATE production_company SET proco_name = ? WHERE proco_id = ?");

            // Plug in the parameters

            statement.setInt(1, productionCompany.getId());
            statement.setString(1, productionCompany.getName());

            // Update the row

            statement.executeUpdate();

        } catch (Exception e) {

            System.out.println("PANIC: Error updating production company");
            System.out.println("ERROR: " + e.getMessage());

        }

        DBConnection.closeConnection(con);

    }

    public static ArrayList<ProductionCompany> getAll() {

        ArrayList<ProductionCompany> productionCompanies = new ArrayList<ProductionCompany>();
        Connection con = DBConnection.createConnection();

        try {

            PreparedStatement statement = con.prepareStatement("SELECT * FROM production_company");
            ResultSet res = statement.executeQuery();

            while(res.next()) {

                ProductionCompany productionCompany = new ProductionCompany();

                Integer id = res.getInt("proco_id");
                String name = res.getString("proco_name");

                productionCompany.setId(id);
                productionCompany.setName(name);

                productionCompanies.add(productionCompany);

            }

        } catch (Exception e) {

            System.out.println("PANIC: Failed to get all production companies");
            System.out.println("ERROR: " + e.getMessage());

        }

        return productionCompanies;

    }

    public static ProductionCompany get(Integer id) {

        Connection con = DBConnection.createConnection();
        ProductionCompany productionCompany = new ProductionCompany();

        try {

            PreparedStatement statement = con.prepareStatement("SELECT proco_name FROM production_company WHERE proco_id = ?");
            statement.setInt(1, id);
            ResultSet res = statement.executeQuery();

            if (res.next()) {

                String productionCompanyName = res.getString("proco_name");

                productionCompany.setId(id);
                productionCompany.setName(productionCompanyName);

            }

        } catch (Exception e) {

            System.out.println("PANIC: Failed to get production company");
            System.out.println("ERROR: " + e.getMessage());

        }

        return productionCompany;

    }

}