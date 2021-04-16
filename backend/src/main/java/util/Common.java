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

}
