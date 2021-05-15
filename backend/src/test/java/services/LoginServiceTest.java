package services;

import static org.junit.jupiter.api.Assertions.*;

class LoginServiceTest {

    @org.junit.jupiter.api.Test
    void authenticate() {
        String username = "geralt";
        String password = "monsterslayer";
        LoginService loginService = new LoginService(username, password);
        String result = loginService.authenticate();
        assertEquals("abc", result);
    }
}