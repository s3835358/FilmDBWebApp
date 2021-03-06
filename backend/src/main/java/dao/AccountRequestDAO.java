package dao;

import models.AccountRequest;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AccountRequestDAO {

    public static void update(AccountRequest accountRequest) {

        Connection con = DBConnection.createConnection();

        try {

            PreparedStatement statement = con.prepareStatement("UPDATE account SET user_approved = ? WHERE username = ? AND user_approved = 0");
            statement.setInt(1, accountRequest.getUserApproved());
            statement.setString(2, accountRequest.getUsername());

            statement.executeUpdate();

        } catch (Exception e) {

            System.out.println("PANIC: Failed to update account request");
            System.out.println("ERROR: " + e.getMessage());

        }

    }

    public static AccountRequest get(String username) {

        AccountRequest accountRequest = new AccountRequest();
        Connection con = DBConnection.createConnection();

        try {

            PreparedStatement statement = con.prepareStatement("SELECT CONCAT(first_name, ' ', last_name) AS 'name', username, email, user_type, proco_name, phone_number FROM account LEFT JOIN production_company ON production_company.proco_id = account.production_company WHERE username = ? AND user_approved = 0");
            statement.setString(1, username);

            ResultSet res = statement.executeQuery();

            if (res.next()) {

                String name = res.getString("name");
                String email = res.getString("email");
                Integer userType = res.getInt("user_type");
                String procoName = res.getString("proco_name");
                String phoneNumber = res.getString("phone_number");

                accountRequest.setName(name);
                accountRequest.setUsername(username);
                accountRequest.setEmail(email);
                accountRequest.setUserType(userType);
                accountRequest.setProcoName(procoName);
                accountRequest.setName(phoneNumber);
                accountRequest.setUserApproved(0);

            }

        } catch (Exception e) {

            System.out.println("PANIC: Failed to get account request");
            System.out.println("ERROR: " + e.getMessage());

        }

        return accountRequest;

    }

    public static ArrayList<AccountRequest> getAll() {

        ArrayList<AccountRequest> accountRequests = new ArrayList<AccountRequest>();
        Connection con = DBConnection.createConnection();

        try {

            PreparedStatement statement = con.prepareStatement("SELECT CONCAT(first_name, ' ', last_name) AS 'name', username, email, user_type, proco_name, phone_number FROM account LEFT JOIN production_company ON production_company.proco_id = account.production_company WHERE user_approved = 0");
            ResultSet res = statement.executeQuery();

            while (res.next()) {

                AccountRequest accountRequest = new AccountRequest();

                String name = res.getString("name");
                String username = res.getString("username");
                String email = res.getString("email");
                Integer userType = res.getInt("user_type");
                String procoName = res.getString("proco_name");
                String phoneNumber = res.getString("phone_number");

                accountRequest.setName(name);
                accountRequest.setUsername(username);
                accountRequest.setEmail(email);
                accountRequest.setUserType(userType);
                accountRequest.setProcoName(procoName);
                accountRequest.setName(phoneNumber);

                accountRequests.add(accountRequest);

            }

        } catch (Exception e) {

            System.out.println("PANIC: Failed to get all account requests");
            System.out.println("ERROR: " + e.getMessage());

        }

        return accountRequests;

    }


}
