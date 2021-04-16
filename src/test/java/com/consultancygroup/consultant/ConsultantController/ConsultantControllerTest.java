package com.consultancygroup.consultant.ConsultantController;


import com.consultancygroup.consultant.ConsultantService.ConsultantService;
import com.consultancygroup.consultant.Model.Consultant;
import com.consultancygroup.consultant.Model.ConsultantResume;
import org.h2.schema.Constant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.Assert;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

// Expecting to get expected response and HTTP.status
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class ConsultantControllerTest {
    @Autowired
    private TestRestTemplate testRestTemplate;
    //Testing of service layer should be independent of the controller layer
    @MockBean
    private ConsultantService consultantMockService;

    @Test
    public void testSaveUserValidConsultant() throws  Exception{
        // Arrange
        Consultant request = new Consultant(1L,"Andre", 20, ConsultantResume.JUNIOR);
        Consultant expectedResponse = new Consultant(1L,"Andre", 20, ConsultantResume.JUNIOR);
        when(consultantMockService.saveConsultant(any(Consultant.class))).thenReturn(expectedResponse);
        String endpoint = "/consultant";
        ResponseEntity<String> responseEntity =
                testRestTemplate.postForEntity(endpoint, request, String.class);

        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }


    @Test
    public void testUserUnderAge() throws  Exception{
        // Arrange
        Consultant request = new Consultant(1L,"Andre", 17, ConsultantResume.JUNIOR);
        Consultant expectedResponse = new Consultant(1L,"Andre", 17, ConsultantResume.JUNIOR);
        when(consultantMockService.saveConsultant(any(Consultant.class))).thenReturn(expectedResponse);
        String endpoint = "/consultant";
        ResponseEntity<String> responseEntity =
                testRestTemplate.postForEntity(endpoint, request, String.class);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }
    @Test
    public void testUserOverAge() throws  Exception{
        // Arrange
        Consultant request = new Consultant(1L,"Andre", 90, ConsultantResume.JUNIOR);
        Consultant expectedResponse = new Consultant(1L,"Andre", 90, ConsultantResume.JUNIOR);
        when(consultantMockService.saveConsultant(any(Consultant.class))).thenReturn(expectedResponse);
        String endpoint = "/consultant";
        ResponseEntity<String> responseEntity =
                testRestTemplate.postForEntity(endpoint, request, String.class);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }







}
