package services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegisterServiceTest {

    @Test
    void createAccount() {
        String username = "pokemaster123";
        String password = "charizard";
        String email = "pikachu@gmail.com";
        String country = "Bahamas";
        String gender = "Male";
        String firstName = "ash";
        String lastName = "ketchup";
        String token = "mmo";
        String zipCode = "00000";
        Integer birthYear = 1999;
        Integer userType = 4;
        String phoneNumber = "451200977";
        Integer productionCompanyId = 0400123456;

        RegisterService registerService = new RegisterService(username, password, email, country, gender, firstName,
                lastName, zipCode, birthYear, userType, phoneNumber, productionCompanyId);
        Boolean result = registerService.createAccount();
        assertTrue(result);
    }
}