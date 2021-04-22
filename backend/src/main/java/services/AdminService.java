package services;

import dao.AccountRequestDAO;
import models.AccountRequest;
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

    public static JSONObject getAccountRequests() {

        JSONObject output = new JSONObject();
        JSONArray requests = new JSONArray();
        ArrayList<AccountRequest> accountRequests = AccountRequestDAO.getAll();
        int k = 0;

        for (AccountRequest i : accountRequests) {

            JSONObject request = new JSONObject();

            request.put("name", i.getName());
            request.put("username", i.getUsername());
            request.put("email", i.getEmail());
            request.put("user_type", i.getEmail());
            request.put("proco_name", i.getProcoName());
            request.put("phone_number", i.getPhoneNumber());

            requests.put(k, request);

            k++;

        }

        output.put("success", true);
        output.put("requests", requests);

        return output;

    }

}
