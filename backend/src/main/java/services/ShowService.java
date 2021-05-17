package services;

import dao.GenreDAO;
import dao.ProductionCompanyDAO;
import dao.ShowDAO;
import models.Genre;
import models.ProductionCompany;
import models.Show;
import org.json.JSONArray;
import org.json.JSONObject;
import util.Common;
import util.Config;

import java.util.ArrayList;

public class ShowService {

    public static JSONObject getShow(int showId) {

        Show show = ShowDAO.get(showId);
        JSONObject output = new JSONObject();
        JSONObject sh = new JSONObject();

        Genre g = GenreDAO.get(show.getGenre());
        ProductionCompany pco = ProductionCompanyDAO.get(show.getProcoId());

        sh.put("id", show.getId());
        sh.put("title", show.getTitle());
        sh.put("genre", g.getName());
        sh.put("length", show.getLength());
        sh.put("type", show.getType());
        sh.put("production_company", pco.getName());
        sh.put("year", show.getYear());

        output.put("success", true);
        output.put("show", sh);

        return output;

    }

    public static JSONObject getAllShows() {

        ArrayList<Show> shows = ShowDAO.getAll();
        JSONObject output = new JSONObject();
        JSONArray sh = new JSONArray();
        int k = 0;

        for (Show i : shows) {

            JSONObject s = new JSONObject();
            Genre g = GenreDAO.get(i.getGenre());

            s.put("id", i.getId());
            s.put("title", i.getTitle());
            s.put("genre", g.getName());

            sh.put(k, s);

            k++;

        }

        output.put("success", true);
        output.put("shows", sh);

        return output;

    }

    public static JSONObject add(String title, int genre, double length, String type, int year, int procoId, int userType) {

        JSONObject resp = new JSONObject();

        Genre genreObj = GenreDAO.get(genre);
        ProductionCompany productionCompanyObj = ProductionCompanyDAO.get(procoId);

        if (title.isEmpty()) {

            resp.put("success", false);
            resp.put("message", "You have left the title of the movie empty.");

        } else if (genreObj.getId() < 1) {

            resp.put("success", false);
            resp.put("message", "You have selected an invalid genre.");

        } else if (length < 0.03) {

            resp.put("success", false);
            resp.put("message", "The length of the show must be at least 3 minutes long.");

        } else if (!type.equals("MOVIE") && !type.equals("SERIES")) {

            resp.put("success", false);
            resp.put("message", "The type of the show selected is not valid.");

        } else if (year < 1800) {

            resp.put("success", false);
            resp.put("message", "The year of the release must be greater than 1800.");

        } else if (productionCompanyObj.getId() < 1) {

            resp.put("success", false);
            resp.put("message", "The production company does not exist. Please choose a valid company from the list.");

        } else {

            long unixTime = System.currentTimeMillis() / 1000L;
            int status = 0;

            if (userType == 2) {
                status = 3;
            }

            // 1: Approved
            // 2: Rejected
            // 3: Timer Approval (24-hr)
            // 0: Admin Approval Required

            Show show = new Show();
            show.setTitle(title);
            show.setGenre(genre);
            show.setLength(length);
            show.setProcoId(procoId);
            show.setType(type);
            show.setYear(year);
            show.setAddedOn(unixTime);
            show.setStatus(status);

            ShowDAO.add(show);

            resp.put("success", true);
            resp.put("message", "Your show has been added to the database. It will be live after admin approval.");

        }

        return resp;

    }

    public static void processAutoApprovals() {

        long unixTime = System.currentTimeMillis() / 1000L;
        ArrayList<Show> shows = ShowDAO.getPendingAutoApproval();

        for (Show i : shows) {

            if (unixTime - i.getAddedOn() > Config.SECONDS_IN_DAY) {

                i.setStatus(1);
                ShowDAO.update(i);

            }

        }

    }

}
