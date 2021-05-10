package services;

import dao.AccountRequestDAO;
import dao.ShowDAO;
import models.AccountRequest;
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
