package com.consultancygroup.registration.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.consultancygroup.registration.models.UserRequest;
import com.consultancygroup.registration.models.UserResponse;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class UserControllerIntegrationTest {

    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private TestRestTemplate testRestTemplate;



    @Test
    public void testCreateValidUser() throws Exception{
        UserRequest userRequest = new UserRequest("Andre' Vella","Ghaxaq",20);
        UserResponse expectedUserResponse = new UserResponse(1L,"Andre' Vella","Ghaxaq",20);
        String expectedResponseBody = om.writeValueAsString(expectedUserResponse);

        String endpoint = "/users";
        //ResponseEntity<UserResponse> responseEntity =
              //  testRestTemplate.postForEntity(endpoint,userRequest,UserResponse.class);
       //USING MOCKITO FRAMEWORK
        ResponseEntity<String> responseEntity =
                testRestTemplate.postForEntity(endpoint,userRequest,String.class);
        assertEquals(HttpStatus.CREATED,responseEntity.getStatusCode());
        /*
        asserts status code is valid
         */
        //assertEquals(expectedUserResponse,responseEntity.getBody());
        /*
        This compares objects in memory rather than the actual content
         */
        JSONAssert.assertEquals(expectedResponseBody,responseEntity.getBody(), true);




    }

}
