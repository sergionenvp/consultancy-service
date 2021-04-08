package com.consultancies.registration.model;

import org.junit.jupiter.api.*;

public class RegistrationRequestTests {
    RegistrationRequest registrationRequest;

    @BeforeEach
    public  void setup() {
        registrationRequest = new RegistrationRequest("Andre Vella",18);

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
    public void testRequestSetAgeMethod(){
        registrationRequest.setAge(20);
        int age = registrationRequest.getAge();
        Assertions.assertEquals(20,age);




    }
    @Test
    public void testRequestSetFullNameMethod(){
        registrationRequest.setFullName("Sergio P.");
        registrationRequest.setFullName("Kelsey M.");

        String fullName = registrationRequest.getFullName();
        Assertions.assertEquals("Kelsey M.",fullName);




    }





}
