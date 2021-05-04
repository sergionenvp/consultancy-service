package com.consultancygroup.accountancy.accountancyController;

import com.consultancygroup.accountancy.accountancyService.AccountancyService;
import com.consultancygroup.accountancy.model.ConsultantResume;
import com.consultancygroup.accountancy.model.Payment;
import com.consultancygroup.accountancy.model.Worker;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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

    private Worker worker1;
    private Worker worker2;
    private Worker worker3;

    @BeforeEach
    private void setUp() {
        worker1 = new Worker(1L,0, ConsultantResume.EXECUTIVE);
        worker2 = new Worker(2L,0, ConsultantResume.EXECUTIVE);
        worker3 = new Worker(3L,0, ConsultantResume.EXECUTIVE);
    }

    @Test
    public void testCreateValidPayment() throws JsonProcessingException, JSONException {
        Payment payment = new Payment(1L, "Sergio", "12212", null, "334", worker1.getId(),2);
        Payment expectedPayment = new Payment(1L, "Sergio", "12212", null, "334", worker1.getId(),2);

        String endpoint = "/payments";
        String expectedResponseBody = om.writeValueAsString(expectedPayment);

        //Service layer expected Payment
        Payment servicePayment = new Payment(1L, "Sergio", "12212", null, "334", worker1.getId(),2);
        when(accountancyMockService.savePayment(any(Payment.class))).thenReturn(servicePayment);

        //Controller layer getting the created and saved Payment.
        ResponseEntity<String> responseEntity = testRestTemplate.postForEntity(endpoint, payment, String.class);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        JSONAssert.assertEquals(expectedResponseBody, responseEntity.getBody(), true);

        verify(accountancyMockService, times(1)).savePayment(any(Payment.class));
    }

    @Test
    public void testCreatePaymentWithNegativePrice() throws JsonProcessingException, JSONException {
        Payment payment = new Payment(1L, "Sergio", "12212", null, "334", worker1.getId(), -3);
        Payment expectedPayment = new Payment(1L, "Sergio", "12212", null, "334", worker1.getId(), -3);

        String endpoint = "/payments";

        ResponseEntity<String> responseEntity = testRestTemplate.postForEntity(endpoint, payment, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void testGetInvalidPayment(){
        String endpoint = "/payments/2";
        ResponseEntity<String> responseEntity = testRestTemplate.getForEntity(endpoint, String.class);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void testCreatePaymentWithPriceEqualsTo0() throws JsonProcessingException, JSONException {
        Payment payment = new Payment(1L,"Sergio", "12212", null, "334", worker1.getId(), 0);
        Payment expectedPayment = new Payment(1L, "Sergio", "12212", null, "334",  worker1.getId(),0);

        String endpoint = "/payments";
        String expectedJSONBody = om.writeValueAsString(expectedPayment);

        Payment servicePayment = new Payment(1L, "Sergio", "12212", null, "334", worker1.getId(), 0);
        when(accountancyMockService.savePayment(any(Payment.class))).thenReturn(servicePayment);

        ResponseEntity<String> responseEntity = testRestTemplate.postForEntity(endpoint, payment, String.class);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        JSONAssert.assertEquals(expectedJSONBody, responseEntity.getBody(), true);

        verify(accountancyMockService, times(1)).savePayment(any(Payment.class));
    }

    @Test
    public void testGetPaymentByIdValidId() throws JsonProcessingException, JSONException {
        Payment payment = new Payment(1L,"Sergio", "12212", null, "334", worker1.getId(), 0);

        String expectedJSONResponseBody = om.writeValueAsString(payment);
        String endpoint = "/payments/" + payment.getId();

        Payment servicePayment = new Payment(1L,"Sergio", "12212", null, "334", worker1.getId(), 0);
        when(accountancyMockService.getPaymentById(any(Long.class))).thenReturn(servicePayment);

        ResponseEntity<String> responsePayment = testRestTemplate.getForEntity(endpoint, String.class);

        assertEquals(HttpStatus.OK, responsePayment.getStatusCode());
        JSONAssert.assertEquals(expectedJSONResponseBody, responsePayment.getBody(), true);

        verify(accountancyMockService, times(1)).getPaymentById(any(Long.class));
    }

    @Test
    public void testGetAllPaymentsValidPayments() throws JsonProcessingException, JSONException {
        Payment payment1 = new Payment(1L,"Sergio", "12212", null, "334", worker1.getId(), 0);
        Payment payment2 = new Payment(2L,"Sara", "8899", null, "334", worker2.getId(),20);
        Payment payment3 = new Payment(3L,"Ana", "12332322", null, "334", worker3.getId(), 10);
        List<Payment> payments = new ArrayList<Payment>(){ {add(payment1); add(payment2); add(payment3);} };
        String expectedJSONPayments = om.writeValueAsString(payments);

        List<Payment> servicePayments = new ArrayList<Payment>(){ {add(payment1); add(payment2); add(payment3);} };
        when(accountancyMockService.getAllPayments()).thenReturn(servicePayments);

        String endpoint = "/payments/all";
        ResponseEntity<String> responsePayments = testRestTemplate.getForEntity(endpoint, String.class);

        assertEquals(HttpStatus.OK, responsePayments.getStatusCode());
        JSONAssert.assertEquals(expectedJSONPayments, responsePayments.getBody(), true);

        verify(accountancyMockService, times(1)).getAllPayments();
    }

    @Test
    public void testGetAllPaymentsById() throws JsonProcessingException, JSONException {
        Payment payment1 = new Payment(1L,"Sergio", "12212", null, "334", worker1.getId(), 0);
        Payment payment2 = new Payment(2L,"Sara", "8899", null, "334", worker2.getId(),20);
        Payment payment3 = new Payment(3L,"Ana", "12332322", null, "334", worker3.getId(), 10);
        List<Payment> payments = new ArrayList<Payment>(){ {add(payment1); add(payment3);} };
        String expectedJSONPayments = om.writeValueAsString(payments);

        List<Long> ids = new ArrayList<Long>();
        ids.add(1L);
        ids.add(3L);
        List<Payment> servicePayments = new ArrayList<Payment>(){ {add(payment1); add(payment3);} };
        when(accountancyMockService.getAllPaymentsById(ids)).thenReturn(servicePayments);

        String endpoint = "/payments/all/1,3";
        ResponseEntity<String> responsePayments = testRestTemplate.getForEntity(endpoint, String.class);

        assertEquals(HttpStatus.OK, responsePayments.getStatusCode());
        JSONAssert.assertEquals(expectedJSONPayments, responsePayments.getBody(), true);

        verify(accountancyMockService, times(1)).getAllPaymentsById(ids);

    }

    @Test
    public void testEditValidPayment() throws JsonProcessingException, JSONException {
        Payment previousPayment = new Payment(1L,"AnaMaria", "8899", null, "168", worker1.getId(),41);
        when(accountancyMockService.getPaymentById(1L)).thenReturn(previousPayment);

        Payment payment = new Payment(1L,"Sergio", "12212", null, "334", worker1.getId(), 0);
        Payment expectedPayment = new Payment(1L,"Sergio", "12212", null, "334", worker1.getId(), 0);
        when(accountancyMockService.savePayment(any(Payment.class))).thenReturn(expectedPayment);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(om.writeValueAsString(payment), headers);

        String endpoint = "/payments/update/1";

        ResponseEntity<String> response = testRestTemplate.exchange(endpoint, HttpMethod.PUT, entity, String.class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        JSONAssert.assertEquals(om.writeValueAsString(payment), response.getBody(), true);

        verify(accountancyMockService, times(1)).getPaymentById(1L);
        verify(accountancyMockService, times(1)).savePayment(any(Payment.class));
    }

    @Test
    public void testDeleteValidPaymentById() {
        doNothing().when(accountancyMockService).deletePaymentById(1L);

        HttpEntity<String> httpEntity = new HttpEntity<>(null, new HttpHeaders());

        String endpoint = "/payments/delete/1";

        ResponseEntity<String> response = testRestTemplate.exchange(endpoint, HttpMethod.DELETE, httpEntity, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(accountancyMockService, times(1)).deletePaymentById(1L);
    }

    @Test
    public void testExport() throws JsonProcessingException {
        Payment payment1 = new Payment(1L,"Sergio", "12212", null, "334", worker1.getId(), 0);
        Payment payment2 = new Payment(2L,"Laura", "12212", null, "334", worker2.getId(), 0);
        List<Payment> expectedConsultants = new LinkedList<Payment>();
        expectedConsultants.add(payment1);
        expectedConsultants.add(payment2);
        String endpoint = "/payments/export";
        ResponseEntity<String> responseEntity = testRestTemplate.getForEntity(endpoint, String.class);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testDeleteAllPayments() {
        doNothing().when(accountancyMockService).deleteAllPayments();

        HttpEntity<String> httpEntity = new HttpEntity<>(null, new HttpHeaders());

        String endpoint = "/payments/delete/all";

        ResponseEntity<String> response = testRestTemplate.exchange(endpoint, HttpMethod.DELETE, httpEntity, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(accountancyMockService, times(1)).deleteAllPayments();
    }

}
