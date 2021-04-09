package com.consultancies.registration.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RegistrationTests {
    RegistrationRequest registrationRequest;
    RegistrationResponse registrationResponse;
    @BeforeEach
    public void setup(){
        registrationRequest = new RegistrationRequest("Andre Vella",18);
        registrationResponse = new RegistrationResponse("Kelsey Marie",18);
    }
    @AfterEach
    public void teardown() {
        registrationRequest = null;
    }
    @Test
    public void testRequestCreation() {
        String fullName = registrationRequest.getFullName();
        int age = registrationRequest.getAge();
        Assertions.assertEquals("Andre Vella",fullName);
        Assertions.assertEquals(18,age);

    }
    @Test
    public void testingRequestSetMethods() {
        String fullName = "Kelsey Marie";
        fullName = "Sergio Pineda";
        int age = 25;
        registrationRequest.setFullName(fullName);
        registrationRequest.setAge(age);
        Assertions.assertEquals("Sergio Pineda",registrationRequest.getFullName());
        Assertions.assertEquals(25,registrationRequest.getAge());



    }

    @Test
    public void testResponseCreation() {
        String fullName = registrationResponse.getFullName();
        int age = registrationResponse.getAge();
        Assertions.assertEquals("Kelsey Marie",fullName);
        Assertions.assertEquals(18,age);

    }
    @Test
    public void testingResponseSetMethods() {
        String fullName = "Kelsey Marie";
        fullName = "Andre Vella";
        int age = 25;
        registrationResponse.setFullName(fullName);
        registrationResponse.setAge(age);
        Assertions.assertEquals("Andre Vella",registrationResponse.getFullName());
        Assertions.assertEquals(25,registrationResponse.getAge());



    }



}
