package services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import util.Config;

import static org.junit.jupiter.api.Assertions.*;

class LoginServiceTest {

    @BeforeAll
    static void init() {
        Config.loadConfig();
    }

    @Test
    void successfulAuthentication() {

        LoginService loginService = new LoginService("geralt", "monsterslayer");

        assertNotNull(loginService.authenticate());

    }

    @Test
    void unsuccessfulAuthentication() {

        LoginService loginService = new LoginService("badusername", "badpassword");

        assertNull(loginService.authenticate());

    }

}