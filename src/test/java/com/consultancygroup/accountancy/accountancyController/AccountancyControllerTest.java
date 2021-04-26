package com.consultancygroup.accountancy.accountancyController;



import com.consultancygroup.accountancy.accountancyService.AccountancyService;
import com.consultancygroup.accountancy.model.Payment;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class AccountancyControllerTest {

    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private TestRestTemplate testRestTemplate;

    @MockBean
    private AccountancyService accountancyMockService;

    @Autowired
    private ModelMapper modelMapper;

    @Test
    public void testCreateValidPayment() throws JsonProcessingException, JSONException {

        Payment payment = new Payment(1L, "Sergio", "12212", null, "334", 2);
        Payment expectedPayment = new Payment(1L, "Sergio", "12212", null, "334", 2);

        String endpoint = "/payments";
        String expectedReponseBody = om.writeValueAsString(expectedPayment);

        //Service layer expected Payment
        Payment servicePayment = new Payment(1L, "Sergio", "12212", null, "334", 2);
        when(accountancyMockService.savePayment(any(Payment.class))).thenReturn(servicePayment);

        //Controller layer getting the created and saved Payment.
        ResponseEntity<String> responseEntity = testRestTemplate.postForEntity(endpoint, payment, String.class);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        JSONAssert.assertEquals(expectedReponseBody, responseEntity.getBody(), true);
    }

    @Test
    public void testCreatePaymentWithNegativePrice() throws JsonProcessingException, JSONException {

        Payment payment = new Payment(1L, "Sergio", "12212", null, "334", -3);
        Payment expectedPayment = new Payment(1L, "Sergio", "12212", null, "334", -3);

        String endpoint = "/payments";

        ResponseEntity<String> responseEntity = testRestTemplate.postForEntity(endpoint, payment, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void testCreatePaymentWithPriceEqualsTo0() throws JsonProcessingException, JSONException {

        Payment payment = new Payment(1L, "Sergio", "12212", null, "334", 0);
        Payment expectedPayment = new Payment(1L, "Sergio", "12212", null, "334", 0);

        String endpoint = "/payments";
        String expectedBody = om.writeValueAsString(expectedPayment);

        Payment servicePayment = new Payment(1L, "Sergio", "12212", null, "334", 0);
        when(accountancyMockService.savePayment(any(Payment.class))).thenReturn(servicePayment);

        ResponseEntity<String> responseEntity = testRestTemplate.postForEntity(endpoint, payment, String.class);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        JSONAssert.assertEquals(expectedBody, responseEntity.getBody(), true);
    }
}
