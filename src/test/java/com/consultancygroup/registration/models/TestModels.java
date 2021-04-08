package com.consultancygroup.registration.models;

import org.junit.jupiter.api.*;

public class TestModels {
    RegistrationRequest registrationRequest;
    RegistrationResponse registrationResponse;
    @BeforeEach
    public void setup() {
        registrationRequest = new RegistrationRequest("Andre Vella",18,UserType.customer);
        registrationResponse = new RegistrationResponse("Andre Vella",18,UserType.customer);


    }
    @AfterEach
    public void teardown() {

        registrationRequest = null;

    }


    @Test
    public void testValidRequest() {
        String fullName=registrationRequest.getFullName();
        int age=registrationRequest.getAge();
        UserType userType=registrationRequest.getUserType();
        Assertions.assertEquals("Andre Vella",fullName);
        Assertions.assertEquals(18,age);
        Assertions.assertEquals(UserType.customer,userType);
    }

    @Test
    public void testValidResponse() {
        String fullName=registrationResponse.getFullName();
        int age=registrationResponse.getAge();
        UserType userType=registrationResponse.getUserType();
        Assertions.assertEquals("Andre Vella",fullName);
        Assertions.assertEquals(18,age);
        Assertions.assertEquals(UserType.customer,userType);
    }

}
