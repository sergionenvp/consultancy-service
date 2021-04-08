package com.consultancygroup;

import com.consultancygroup.registration.models.RegistrationRequest;
import com.consultancygroup.registration.models.UserType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class testingModels {

    @Test
    public void testRegistrationRequestCreationValidity() {
        RegistrationRequest registrationRequest = new RegistrationRequest("Andre Vella",18, UserType.customer );
        String fullName = registrationRequest.getFullName();
        int age = registrationRequest.getAge();
        UserType userType = registrationRequest.getUserType();
        Assertions.assertEquals("Andre Vella",fullName);
        Assertions.assertEquals(18,age);
        Assertions.assertEquals(UserType.customer,userType);
    }

}
