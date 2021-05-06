package com.consultancygroup.accountancy.accountancyController;

import com.consultancygroup.accountancy.accountancyService.AccountancyService;
import com.consultancygroup.accountancy.model.ConsultantResume;
import com.consultancygroup.accountancy.model.Payment;
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
import springfox.documentation.spring.web.json.Json;

import java.text.DecimalFormat;
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

    private static DecimalFormat df2 = new DecimalFormat("#.##");
    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private TestRestTemplate testRestTemplate;

    @MockBean
    private AccountancyService accountancyMockService;

    @Autowired
    private ModelMapper modelMapper;

    private Payment payment1;
    private Payment payment2;
    private Payment payment3;

    @BeforeEach
    public void setUp(){
        payment1 = new Payment(1L, "Sergio", "12212", null, "334", 1L, ConsultantResume.EXECUTIVE,3);
        payment2 = new Payment(2L, "Ana", "3456456", null, "334", 2L, ConsultantResume.EXECUTIVE, 3);
        payment3 = new Payment(3L, "Sara", "3456", null, "334", 3L, ConsultantResume.EXECUTIVE, 3);
    }

    @Test
    public void testCreateValidPayment() throws JsonProcessingException, JSONException {
        Payment payment = new Payment(1L, "Sergio", "12212", null, "334", 1L,ConsultantResume.EXECUTIVE, 2);
        Payment expectedPayment = new Payment(1L, "Sergio", "12212", null, "334", 1L, ConsultantResume.EXECUTIVE, 2);

        String endpoint = "/payments";
        String expectedResponseBody = om.writeValueAsString(expectedPayment);

        //Service layer expected Payment.
        Payment servicePayment = new Payment(1L, "Sergio", "12212", null, "334", 1L, ConsultantResume.EXECUTIVE,2);
        when(accountancyMockService.savePayment(any(Payment.class))).thenReturn(servicePayment);

        //Controller layer getting the created and saved Payment.
        ResponseEntity<String> responseEntity = testRestTemplate.postForEntity(endpoint, payment, String.class);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        JSONAssert.assertEquals(expectedResponseBody, responseEntity.getBody(), true);

        verify(accountancyMockService, times(1)).savePayment(any(Payment.class));
    }

    @Test
    public void testGetWorkerMoneyByPaymentId() throws JsonProcessingException, JSONException {
        Payment payment = new Payment(1L, "Sergio", "12212", null, "334", 1L,ConsultantResume.EXECUTIVE, 2);
        double expectedProfit = 2.7;

        String endpoint = "/payments/salary/payment/" + payment.getId();
        String expectedResponseBody = om.writeValueAsString(expectedProfit);

        //Service layer expected double.
        double d = 2.7;
        when(accountancyMockService.getWorkerProfitByPaymentId(any(Long.class))).thenReturn(d);

        //Controller layer getting the created and saved Payment.
        ResponseEntity<String> responseEntity = testRestTemplate.getForEntity(endpoint, String.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        JSONAssert.assertEquals(expectedResponseBody, responseEntity.getBody(), true);

        verify(accountancyMockService, times(1)).getWorkerProfitByPaymentId(any(Long.class));
    }

    @Test
    public void testGetWorkerBalanceByWorkerId() throws JsonProcessingException, JSONException {
        double expectedProfit = 2.7;

        String endpoint = "/payments/salary/worker/" + payment1.getWorkerId();
        String expectedResponseBody = om.writeValueAsString(expectedProfit);

        //Service layer expected double.
        double d = 2.7;
        when(accountancyMockService.getWorkerBalanceByWorkerId(any(Long.class))).thenReturn(d);

        //Controller layer getting the created and saved Payment.
        ResponseEntity<String> responseEntity = testRestTemplate.getForEntity(endpoint, String.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        JSONAssert.assertEquals(expectedResponseBody, responseEntity.getBody(), true);

        verify(accountancyMockService, times(1)).getWorkerBalanceByWorkerId(any(Long.class));
    }

    @Test
    public void testCreatePaymentWithNegativePrice() throws JsonProcessingException, JSONException {
        Payment payment = new Payment(1L, "Sergio", "12212", null, "334", 1L, ConsultantResume.EXECUTIVE,-3);
        Payment expectedPayment = new Payment(1L, "Sergio", "12212", null, "334", 1L, ConsultantResume.EXECUTIVE,-3);

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
        Payment payment = new Payment(1L, "Sergio", "12212", null, "334", 1L, ConsultantResume.EXECUTIVE,2);
        Payment expectedPayment = new Payment(1L, "Sergio", "12212", null, "334", 1L, ConsultantResume.EXECUTIVE,2);

        String endpoint = "/payments";
        String expectedJSONBody = om.writeValueAsString(expectedPayment);

        Payment servicePayment = new Payment(1L, "Sergio", "12212", null, "334", 1L, ConsultantResume.EXECUTIVE,2);
        when(accountancyMockService.savePayment(any(Payment.class))).thenReturn(servicePayment);

        ResponseEntity<String> responseEntity = testRestTemplate.postForEntity(endpoint, payment, String.class);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        JSONAssert.assertEquals(expectedJSONBody, responseEntity.getBody(), true);

        verify(accountancyMockService, times(1)).savePayment(any(Payment.class));
    }

    @Test
    public void testGetPaymentByIdValidId() throws JsonProcessingException, JSONException {
        Payment payment = new Payment(1L, "Sergio", "12212", null, "334", 1L, ConsultantResume.EXECUTIVE,2);

        String expectedJSONResponseBody = om.writeValueAsString(payment);
        String endpoint = "/payments/" + payment.getId();

        Payment servicePayment = new Payment(1L, "Sergio", "12212", null, "334", 1L, ConsultantResume.EXECUTIVE,2);
        when(accountancyMockService.getPaymentById(any(Long.class))).thenReturn(servicePayment);

        ResponseEntity<String> responsePayment = testRestTemplate.getForEntity(endpoint, String.class);

        assertEquals(HttpStatus.OK, responsePayment.getStatusCode());
        JSONAssert.assertEquals(expectedJSONResponseBody, responsePayment.getBody(), true);

        verify(accountancyMockService, times(1)).getPaymentById(any(Long.class));
    }

    @Test
    public void testGetAllPaymentsValidPayments() throws JsonProcessingException, JSONException {
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
        List<Payment> payments = new ArrayList<Payment>(){{add(payment1); add(payment3);} };
        List<Long> ids = new ArrayList<Long>();
        ids.add(1L);
        ids.add(3L);
        String expectedJSONPayments = om.writeValueAsString(payments);

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
        Payment previousPayment = new Payment(1L,"AnaMaria", "8899", null, "168", 1L, ConsultantResume.JUNIOR,41);
        when(accountancyMockService.getPaymentById(1L)).thenReturn(previousPayment);

        Payment payment = new Payment(1L,"Sergio", "12212", null, "334", 2L, ConsultantResume.SENIOR, 0);
        Payment expectedPayment = new Payment(1L,"Sergio", "12212", null, "334", 2L, ConsultantResume.SENIOR, 0);
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

    @Test
    public void testFindPaymentsByWorkerId() throws JsonProcessingException, JSONException {
        List<Payment> payments = new ArrayList<>();
        payment2.setWorkerId(1L);
        payment3.setWorkerId(1L);
        payments.add(payment1);
        payments.add(payment2);
        payments.add(payment3);

        String endpoint = "/payments/worker/" + payment1.getWorkerId();

        String expectedJSON = om.writeValueAsString(payments);

        List<Payment> servicePayments = new ArrayList<>(){ {add(payment1); add(payment2); add(payment3);} };;
        when(accountancyMockService.getPaymentsByWorkerId(any(Long.class))).thenReturn(servicePayments);

        ResponseEntity<String> response = testRestTemplate.getForEntity(endpoint, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        JSONAssert.assertEquals(expectedJSON, response.getBody(), true);

        verify(accountancyMockService, times(1)).getPaymentsByWorkerId(1L);
    }

    @Test
    public void testGetCompanyBalance() throws JsonProcessingException, JSONException {
        double companyMoney = 0.6;
        String expectedJSON = om.writeValueAsString(companyMoney);

        double serviceMoney = 0.6;
        when(accountancyMockService.getCompanyBalance()).thenReturn(serviceMoney);

        String endpoint = "/payments/company/balance";
        ResponseEntity<String> responseEntity = testRestTemplate.getForEntity(endpoint, String.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        JSONAssert.assertEquals(expectedJSON, responseEntity.getBody(), true);

        verify(accountancyMockService, times(1)).getCompanyBalance();
    }
}
