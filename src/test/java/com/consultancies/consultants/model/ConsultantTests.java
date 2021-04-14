package com.consultancies.consultants.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ConsultantTests {

    ConsultantRequest consultantRequest;
    ConsultantResponse consultantResponse;
    @BeforeEach
    public void setup(){
        consultantRequest = new ConsultantRequest("Andre Vella",18);
        consultantResponse = new ConsultantResponse(1,"Kelsey Marie",18);
    }
    @AfterEach
    public void teardown() {
        consultantRequest = null;
    }
    @Test
    public void testRequestCreation() {
        String fullName = consultantRequest.getFullName();
        int age = consultantRequest.getAge();
        Assertions.assertEquals("Andre Vella",fullName);
        Assertions.assertEquals(18,age);

    }
    @Test
    public void testingRequestSetMethods() {
        String fullName = "Kelsey Marie";
        fullName = "Sergio Pineda";
        int age = 25;
        consultantRequest.setFullName(fullName);
        consultantRequest.setAge(age);
        Assertions.assertEquals("Sergio Pineda",consultantRequest.getFullName());
        Assertions.assertEquals(25,consultantRequest.getAge());



    }

    @Test
    public void testResponseCreation() {
        String fullName = consultantResponse.getFullName();
        int age = consultantResponse.getAge();
        Assertions.assertEquals("Kelsey Marie",fullName);
        Assertions.assertEquals(18,age);

    }
    @Test
    public void testingResponseSetMethods() {
        String fullName = "Kelsey Marie";
        fullName = "Andre Vella";
        int age = 25;
        consultantResponse.setFullName(fullName);
        consultantResponse.setAge(age);
        Assertions.assertEquals("Andre Vella", consultantResponse.getFullName());
        Assertions.assertEquals(25, consultantResponse.getAge());

    }



}
