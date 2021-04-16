package util;

import java.util.regex.*;

public class Common {

    public static boolean isUsernameValid(String username) {

        String regex = "^[A-Za-z]\\w{2,29}$";
        Pattern p = Pattern.compile(regex);

        if (username == null || username.isEmpty()) {
            return false;
        }

        Matcher m = p.matcher(username);

        return m.matches();

    }

    public static boolean isEmailValid(String email) {

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);

        if (email == null || email.isEmpty()) {
            return false;
        }

        return pat.matcher(email).matches();

    }

}
