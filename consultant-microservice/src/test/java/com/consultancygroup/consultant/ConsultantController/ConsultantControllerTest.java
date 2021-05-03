package com.consultancygroup.consultant.ConsultantController;
import com.consultancygroup.consultant.ConsultantService.ConsultantService;
import com.consultancygroup.consultant.Model.Consultant;
import com.consultancygroup.consultant.Model.ConsultantResume;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.internal.bytebuddy.dynamic.DynamicType;
import org.h2.schema.Constant;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONString;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

// Expecting to get expected response and HTTP.status
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class ConsultantControllerTest {
    private static final ObjectMapper om = new ObjectMapper();
    @Autowired
    private TestRestTemplate testRestTemplate;
    //Testing of service layer should be independent of the controller layer
    @MockBean
    private ConsultantService consultantMockService;

    @Test
    public void testSaveUserValidConsultant() throws Exception {
        // Arrange
        Consultant request = new Consultant(1L, "Andre", 20, 79663256, ConsultantResume.JUNIOR);
        Consultant expectedResponse = new Consultant(1L, "Andre", 20, 79663256, ConsultantResume.JUNIOR);
        when(consultantMockService.saveConsultant(any(Consultant.class))).thenReturn(expectedResponse);
        String endpoint = "/consultant";
        ResponseEntity<String> responseEntity =
                testRestTemplate.postForEntity(endpoint, request, String.class);
        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void testUserUnderAge() throws Exception {
        // Arrange
        Consultant request = new Consultant(1L, "Andre", 17, 79663256, ConsultantResume.JUNIOR);
        Consultant expectedResponse = new Consultant(1L, "Andre", 17, 79663256, ConsultantResume.JUNIOR);
        when(consultantMockService.saveConsultant(any(Consultant.class))).thenReturn(expectedResponse);
        String endpoint = "/consultant";
        ResponseEntity<String> responseEntity =
                testRestTemplate.postForEntity(endpoint, request, String.class);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void testUserOverAge() throws Exception {
        // Arrange
        Consultant request = new Consultant(1L, "Andre", 90, 79663256, ConsultantResume.JUNIOR);
        Consultant expectedResponse = new Consultant(1L, "Andre", 90, 79663256, ConsultantResume.JUNIOR);
        when(consultantMockService.saveConsultant(any(Consultant.class))).thenReturn(expectedResponse);
        String endpoint = "/consultant";
        ResponseEntity<String> responseEntity =
                testRestTemplate.postForEntity(endpoint, request, String.class);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void testGetConsultantByIdValidId() throws JSONException {
        // Arrange
        Consultant consultant = new Consultant(1L, "Andre Vella", 20, 79663256, ConsultantResume.JUNIOR);
        String expectedResponseBody = "{consultantId:1, fullName:\"Andre Vella\", age:20, phoneNumber:79663256, consultantResume:\"JUNIOR\"}";
        when(consultantMockService.findConsultantById(1L)).thenReturn(consultant);
        String endpoint = "/consultant/id/1";
        // Calling service layer
        //Service layer returns some saved consultant
        //The saved consultant should match the expected consulstant
        ResponseEntity<String> responseEntity = testRestTemplate.getForEntity(endpoint, String.class);

        // Assert
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        JSONAssert.assertEquals(expectedResponseBody, responseEntity.getBody(), true);
    }

    @Test
    public void testGetUserByNonExistingId() {
        String endpoint = "/consultant/id/10000000";
        ResponseEntity<String> response = testRestTemplate.getForEntity(endpoint, String.class);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testGetUserByValidFullName() throws JSONException, JsonProcessingException {
        // Arrange
        Consultant consultant1 = new Consultant(1L, "Andre Vella", 20, 79663256, ConsultantResume.JUNIOR);
        Consultant consultant2 = new Consultant(1L, "Kelsey Marie", 20, 79663256, ConsultantResume.JUNIOR);
        List<Consultant> consultants = new LinkedList<Consultant>();
        consultants.add(consultant1);
        consultants.add(consultant2);
        String expectedResponseBody = om.writeValueAsString(consultants);
        when(consultantMockService.findAllByFullName("Andre Vella")).thenReturn(consultants);
        String endpoint = "/consultant/fullName/Andre Vella";
        // Calling service layer
        //Service layer returns some saved consultant
        //The saved consultant should match the expected consulstant
        ResponseEntity<String> responseEntity = testRestTemplate.getForEntity(endpoint, String.class);

        // Assert
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        JSONAssert.assertEquals(expectedResponseBody, responseEntity.getBody(), true);
    }

    @Test
    public void testGetConsultantByNonExistingName() {
        String endpoint = "/consultant/fullName/Andre Vella";
        ResponseEntity<String> response = testRestTemplate.getForEntity(endpoint, String.class);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testDeleteConsultantValidConsultant() {
        doNothing().when(consultantMockService).deleteByConsultantId(1L);
        HttpEntity<String> entity = new HttpEntity<>(null, new HttpHeaders());
        String endpoint = "/consultant/id/1";
        ResponseEntity<String> response = testRestTemplate.exchange(endpoint, HttpMethod.DELETE, entity, String.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        //Checking that 1 consultant is deleted.
        verify(consultantMockService, times(1)).deleteByConsultantId(1L);
    }

    //Testing get userByConsultantResume
    @Test
    public void testGetConsultantsByExistingConsultantResume() throws Exception {
        // Arrange
        Consultant consultant1 = new Consultant(1L, "Sergio", 20, 7777771, ConsultantResume.SENIOR);
        Consultant consultant2 = new Consultant(1L, "Kelsey", 20, 7777774, ConsultantResume.SENIOR);
        List<Consultant> expectedConsultants = new LinkedList<Consultant>();
        expectedConsultants.add(consultant1);
        expectedConsultants.add(consultant2);
        String expectedConsultantsBody = om.writeValueAsString(expectedConsultants);
        when(consultantMockService.findAllByConsultantResume(ConsultantResume.SENIOR)).thenReturn(expectedConsultants);
        String endpoint = "/consultant/consultantResume/SENIOR";
        ResponseEntity<String> responseEntity = testRestTemplate.getForEntity(endpoint, String.class);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(expectedConsultantsBody, responseEntity.getBody());

    }

    @Test
    public void testGetConsultantByNonExistingResume() {
        String endpoint = "/consultant/consultantResume/SENIOR";
        ResponseEntity<String> response = testRestTemplate.getForEntity(endpoint, String.class);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }


    @Test
    public void testGetAllConsultants() throws JsonProcessingException {
        Consultant consultant1 = new Consultant(1L, "Sergio", 20, 7777771, ConsultantResume.SENIOR);
        Consultant consultant2 = new Consultant(1L, "Kelsey", 20, 7777774, ConsultantResume.SENIOR);
        List<Consultant> expectedConsultants = new LinkedList<Consultant>();
        expectedConsultants.add(consultant1);
        expectedConsultants.add(consultant2);
        when(consultantMockService.findAll()).thenReturn(expectedConsultants);
        String expectedBody = om.writeValueAsString(expectedConsultants);
        String endpoint = "/consultant";
        ResponseEntity<String> response = testRestTemplate.getForEntity(endpoint, String.class);
        Assertions.assertEquals(expectedBody, response.getBody());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    //delete
    @Test
    public void testDeleteAllConsultants() throws JsonProcessingException {

        String endpoint = "/consultant/deleteAll";
        ResponseEntity<String> response = testRestTemplate.exchange(endpoint, HttpMethod.DELETE, null, String.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGetConsultantLessThanSomeAge() throws JsonProcessingException {
        // Arrange
        Consultant consultant1 = new Consultant(1L, "Sergio", 20, 7777771, ConsultantResume.SENIOR);
        Consultant consultant2 = new Consultant(1L, "Kelsey", 20, 7777774, ConsultantResume.SENIOR);
        List<Consultant> expectedConsultants = new LinkedList<Consultant>();
        expectedConsultants.add(consultant1);
        expectedConsultants.add(consultant2);
        String expectedConsultantsBody = om.writeValueAsString(expectedConsultants);
        when(consultantMockService.getConsultantsOlderThanMinAge(2)).thenReturn(expectedConsultants);
        String endpoint = "/consultant/ageMinimum/2";
        ResponseEntity<String> responseEntity = testRestTemplate.getForEntity(endpoint, String.class);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(expectedConsultantsBody, responseEntity.getBody());

    }

    @Test
    public void testExport() throws JsonProcessingException {
        // Arrange
        Consultant consultant1 = new Consultant(1L, "Sergio", 20, 7777771, ConsultantResume.SENIOR);
        Consultant consultant2 = new Consultant(1L, "Kelsey", 20, 7777774, ConsultantResume.SENIOR);
        List<Consultant> expectedConsultants = new LinkedList<Consultant>();
        expectedConsultants.add(consultant1);
        expectedConsultants.add(consultant2);
        String endpoint = "/consultant/export";
        ResponseEntity<String> responseEntity = testRestTemplate.getForEntity(endpoint, String.class);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }

    @Test
    public void testUpdateWithValidConsultant() throws Exception {
        Consultant inputConsultant = new Consultant(1L, "Andre Vella", 20, 79663256, ConsultantResume.JUNIOR);
        Consultant expectedResponse = new Consultant(1L, "Andre Vella", 20, 79663256, ConsultantResume.JUNIOR);
        when(consultantMockService.saveConsultant(any(Consultant.class))).thenReturn(expectedResponse);

        Consultant output = new Consultant(1L, "Andre V", 20, 79663256, ConsultantResume.JUNIOR);
        when(consultantMockService.findConsultantById(1L)).thenReturn(output);


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(om.writeValueAsString(inputConsultant), headers);

        String endpoint = "/consultant/id/1";

        // Act
        ResponseEntity<String> response = testRestTemplate.exchange(endpoint, HttpMethod.PUT, entity, String.class);

        // Assert
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        JSONAssert.assertEquals(om.writeValueAsString(inputConsultant), response.getBody(), true);

        verify(consultantMockService, times(1)).findConsultantById(1L);
        verify(consultantMockService, times(1)).saveConsultant(any(Consultant.class));
    }


    @Test
    public void testUpdateUserNonExistentId() throws JsonProcessingException {
        // Arrange
        Consultant newConsultantEdit = new Consultant(1L, "Andre", 20, 79663256, ConsultantResume.JUNIOR);
        Consultant expectedResponse = new Consultant(1L, "Andre", 20, 79663256, ConsultantResume.JUNIOR);
        when(consultantMockService.saveConsultant(any(Consultant.class))).thenReturn(expectedResponse);
        when(consultantMockService.findConsultantById(1L)).thenReturn(null);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(om.writeValueAsString(newConsultantEdit), headers);

        String endpoint = "/consultant/id/1";

        // Act
        ResponseEntity<String> response = testRestTemplate.exchange(endpoint, HttpMethod.PUT, entity, String.class);

        // Assert
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        verify(consultantMockService, times(1)).findConsultantById(1L);
        verify(consultantMockService, never()).saveConsultant(any(Consultant.class));
    }
    @Test
    public void testUpdateUnderAgeConsultant() throws Exception {
        Consultant newConsultantEdit = new Consultant(1L, "Andre", 15, 79663256, ConsultantResume.JUNIOR);
        Consultant someExpectedOutput = new Consultant();
        when(consultantMockService.saveConsultant(newConsultantEdit)).thenReturn(someExpectedOutput);
        Consultant consultantToEdit = new Consultant(1L, "Andre", 20, 79663256, ConsultantResume.JUNIOR);
        when(consultantMockService.findConsultantById(1L)).thenReturn(consultantToEdit);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(om.writeValueAsString(newConsultantEdit), headers);
        String endpoint = "/consultant/id/1";
        ResponseEntity<String> response = testRestTemplate.exchange(endpoint, HttpMethod.PUT, entity, String.class);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

}
