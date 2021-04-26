package com.consultancygroup.accountancy;



import com.consultancygroup.accountancy.accountancyService.AccountancyService;
import com.consultancygroup.accountancy.model.Payment;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class AccountancyControllerTest {

    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private TestRestTemplate testRestTemplate;

    @MockBean
    private AccountancyService accountancyService;

    @Test
    public void testCreateValidPayment(){

        Payment payment = new Payment(1L, "Sergio", "12212", null, "334", 2);
        String endpoint = "/payments";

        ResponseEntity<Payment> responseEntity = testRestTemplate.postForEntity(endpoint, payment, Payment.class);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }
}
