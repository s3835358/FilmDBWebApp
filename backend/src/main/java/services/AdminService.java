package services;

import dao.AccountRequestDAO;
import dao.GenreDAO;
import dao.ProductionCompanyDAO;
import dao.ShowDAO;
import models.AccountRequest;
import models.Genre;
import models.ProductionCompany;
import models.Show;
import org.json.JSONArray;
import org.json.JSONObject;
import util.Common;

import java.util.ArrayList;

public class AdminService {

    public static JSONObject beforeExecutionCheck(String token) {

        JSONObject output = new JSONObject();

        if (!Common.isLoggedIn(token)) {

            output.put("success", false);
            output.put("message", "Your session has expired. Please try logging in again.");

        } else if (!Common.isAdmin(token)) {

            output.put("success", false);
            output.put("message", "You need to be an administrator to access this endpoint.");

        } else {

            output.put("success", true);

        }

        return output;

    }

    public static JSONObject changeUserStatus(String username, int newStatus) {

        JSONObject output = new JSONObject();
        AccountRequest accountRequest = AccountRequestDAO.get(username);

        if (accountRequest.getUsername() == null) {

            output.put("success", false);
            output.put("message", "The account request does not exist!");

        } else {

            accountRequest.setUserApproved(newStatus);

            AccountRequestDAO.update(accountRequest);

            output.put("success", true);
            output.put("message", "The account status has been updated!");

        }

        return output;

    }

    public static JSONObject changeShowStatus(Integer showId, int newStatus) {

        JSONObject output = new JSONObject();
        Show show = ShowDAO.get(showId);

        if (show.getTitle() == null) {

            output.put("success", false);
            output.put("message", "The show does not exist!");

        } else {

            show.setStatus(newStatus);

            ShowDAO.update(show);

            output.put("success", true);
            output.put("message", "The show status has been updated!");

        }

        return output;

    }

    public static JSONObject deleteShow(Integer showId) {

        JSONObject output = new JSONObject();
        Show show = ShowDAO.get(showId);

        if (show.getTitle() == null) {

            output.put("success", false);
            output.put("message", "The show does not exist!");

        } else {

            ShowDAO.delete(show);

            output.put("success", true);
            output.put("message", "The show has been deleted!");

        }

        return output;

    }

    public static JSONObject editShow(Integer showId, String title, String genre, double length, String type, int year, int procoId, int status) {

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

            Show show = ShowDAO.get(showId);

            show.setTitle(title);
            show.setGenre(genreObj.getId());
            show.setLength(length);
            show.setProcoId(procoId);
            show.setType(type);
            show.setYear(year);
            show.setStatus(status);

            ShowDAO.update(show);

            resp.put("success", true);
            resp.put("message", "The show has been updated!");

        }

        return resp;

    }

    public static JSONObject getAccountRequests() {

        JSONObject output = new JSONObject();
        JSONArray requests = new JSONArray();
        ArrayList<AccountRequest> accountRequests = AccountRequestDAO.getAll();
        int k = 0;

        for (AccountRequest i : accountRequests) {

            JSONObject request = new JSONObject();
            String userTypeTranslated;

            if (i.getUserType() == 1) {
                userTypeTranslated = "Normal User";
            } else if (i.getUserType() == 2) {
                userTypeTranslated = "PCo";
            } else if (i.getUserType() == 3) {
                userTypeTranslated = "Critic";
            } else {
                userTypeTranslated = "Admin";
            }

            request.put("name", i.getName());
            request.put("username", i.getUsername());
            request.put("email", i.getEmail());
            request.put("user_type", userTypeTranslated);
            request.put("proco_name", i.getProcoName());
            request.put("phone_number", i.getPhoneNumber());

            requests.put(k, request);

            k++;

        }

        output.put("success", true);
        output.put("requests", requests);

        return output;

    }

    public static JSONObject getPendingShows() {

        JSONObject output = new JSONObject();
        JSONArray pending = new JSONArray();
        ArrayList<Show> pendingShows = ShowDAO.getPending();
        int k = 0;

        for (Show i : pendingShows) {

            JSONObject pendingShow = new JSONObject();

            pendingShow.put("show_id", i.getId());
            pendingShow.put("show_title", i.getTitle());
            pendingShow.put("genre", i.getGenre());
            pendingShow.put("length", i.getLength());
            pendingShow.put("type", i.getType());
            pendingShow.put("proco_id", i.getProcoId());
            pendingShow.put("year", i.getYear());
            pendingShow.put("added_on", i.getAddedOn());
            pendingShow.put("status", i.getStatus());

            pending.put(k, pendingShow);

            k++;

        }

        output.put("success", true);
        output.put("pending", pending);

        return output;

    }

}
