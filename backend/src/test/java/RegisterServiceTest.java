import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import util.Config;
import services.RegisterService;

import static org.junit.jupiter.api.Assertions.*;

class RegisterServiceTest {

    @BeforeAll
    static void init() {
        Config.loadConfig();
    }

    @Test
    void goodRegistrationParameters() {

        String username = "pokemaster123";
        String password = "charizard";
        String email = "pikachu@gmail.com";
        String country = "Bahamas";
        String gender = "Male";
        String firstName = "ash";
        String lastName = "ketchup";
        String zipCode = "00000";
        Integer birthYear = 1999;
        Integer userType = 2;
        String phoneNumber = "451200977";
        Integer productionCompanyId = 1;

        RegisterService registerService = new RegisterService(username, password, email, country, gender, firstName, lastName, zipCode, birthYear, userType, phoneNumber, productionCompanyId);
        JSONObject result = registerService.validate();

        assertTrue(result.getBoolean("success"));

    }

    @Test
    void badRegistrationParameters() {

        String username = "pokemaster123";
        String password = "charizard";
        String email = "pikachu@gmail.com";
        String country = "Bahamas";
        String gender = "Male";
        String firstName = "ash";
        String lastName = "ketchup";
        String zipCode = "00000";
        Integer birthYear = 1999;
        Integer userType = 4;
        String phoneNumber = "451200977";
        Integer productionCompanyId = 145645678;

        RegisterService registerService = new RegisterService(username, password, email, country, gender, firstName, lastName, zipCode, birthYear, userType, phoneNumber, productionCompanyId);
        JSONObject result = registerService.validate();

        assertFalse(result.getBoolean("success"));

    }

}