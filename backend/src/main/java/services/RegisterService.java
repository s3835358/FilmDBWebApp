package services;

import at.favre.lib.crypto.bcrypt.BCrypt;
import dao.ProductionCompanyDAO;
import dao.UserDAO;
import models.ProductionCompany;
import models.User;
import org.json.JSONObject;
import util.Common;

public class RegisterService {

    private final String username;
    private final String password;
    private final String email;
    private final String country;
    private final String gender;
    private final String firstName;
    private final String lastName;
    private final String token;
    private final String zipCode;
    private final Integer birthYear;
    private final Integer userType;
    private final String phoneNumber;
    private final Integer productionCompanyId;

    public RegisterService(String username, String password, String email, String country, String gender, String firstName, String lastName, String zipCode, Integer birthYear, Integer userType, String phoneNumber, Integer productionCompanyId) {

        this.username = username;
        this.password = password;
        this.email = email;
        this.country = country;
        this.gender = gender;
        this.firstName = firstName;
        this.lastName = lastName;
        this.token = "";
        this.zipCode = zipCode;
        this.birthYear = birthYear;
        this.userType = userType;
        this.phoneNumber = phoneNumber;
        this.productionCompanyId = productionCompanyId;

    }

    public JSONObject validate() {

        JSONObject resp = new JSONObject();

        ProductionCompany productionCompany = ProductionCompanyDAO.get(productionCompanyId);

        if (!Common.isUsernameValid(username)) {

            resp.put("success", false);
            resp.put("message", "Your username is not correct.");

        } else if (firstName.isEmpty()) {

            resp.put("success", false);
            resp.put("message", "The first name is not valid.");

        } else if (lastName.isEmpty()) {

            resp.put("success", false);
            resp.put("message", "The last name is not valid.");

        } else if (password.isEmpty() || password.length() < 6) {

            resp.put("success", false);
            resp.put("message", "Your password must be at least 6 characters.");

        } else if (!Common.isEmailValid(email)) {

            resp.put("success", false);
            resp.put("message", "The email address you entered is invalid.");

        } else if (country.isEmpty()) {

            resp.put("success", false);
            resp.put("message", "Please select a valid country from the options.");

        } else if (!gender.equals("Male") && !gender.equals("Female")) {

            resp.put("success", false);
            resp.put("message", "Please select a valid gender.");

        } else if (zipCode.isEmpty()) {

            resp.put("success", false);
            resp.put("message", "Please enter a valid ZIP Code.");

        } else if (birthYear < 1902 || birthYear > 2021) {

            resp.put("success", false);
            resp.put("message", "Please enter a valid birth year (1902 - 2021).");

        } else if (userType < 1 || userType > 3) {

            resp.put("success", false);
            resp.put("message", "Please select a valid user type from the profile.");

        } else if (userType > 1 && phoneNumber.isEmpty()) {

            resp.put("success", false);
            resp.put("message", "Please enter the phone number required.");

        } else if (userType == 2 && productionCompany.getId() < 1) {

            resp.put("success", false);
            resp.put("message", "The production company does not exist. Please choose a valid company from the list.");

        } else if (UserDAO.get(username).getUsername() != null) {

            resp.put("success", false);
            resp.put("message", "The username or email you used already exists at the website.");

        } else {

            resp.put("success", true);

        }

        return resp;

    }

    public Boolean createAccount() {

        User user = new User();
        String passwordHash = BCrypt.withDefaults().hashToString(12, password.toCharArray());
        int userApproved = 1;

        if (userType > 1)
            userApproved = 0;

        user.setUsername(username);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPasswordHash(passwordHash);
        user.setUserType(userType);
        user.setUserApproved(userApproved);
        user.setPhoneNumber(phoneNumber);
        user.setBirthYear(birthYear);
        user.setToken(token);
        user.setCountry(country);
        user.setGender(gender);
        user.setZipCode(zipCode);
        user.setProductionCompany(productionCompanyId);

        return UserDAO.add(user);

    }

}