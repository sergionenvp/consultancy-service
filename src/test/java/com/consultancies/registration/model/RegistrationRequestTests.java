package com.consultancies.registration.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RegistrationRequestTests {

    @Test
    public void testRequestCreation() {
        RegistrationRequest registrationRequest = new RegistrationRequest("Andre Vella",18);
        String fullName = registrationRequest.getFullName();
        int age = registrationRequest.getAge();
        Assertions.assertEquals("Andre Vella",fullName);
        Assertions.assertEquals(18,age);

    }



}
