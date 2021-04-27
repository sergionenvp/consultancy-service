package com.consultancygroup.accountancy.accountancyService;

import com.consultancygroup.accountancy.accountancyRepository.AccountancyRepository;
import com.consultancygroup.accountancy.model.Payment;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class AccountancyServiceTest {

    @MockBean
    AccountancyRepository accountancyRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    AccountancyService accountancyService;

    @Test
    public void testSavePayment(){
        Payment paymentToBeSaved = new Payment(1L,"Sergio", "12212", null, "334", 3);
        Payment expectedSavedPayment = new Payment(1L, "Sergio", "12212", null, "334", 3);

        Payment paymentService = new Payment(1L, "Sergio", "12212", null, "334", 3);
        when(accountancyService.savePayment(any(Payment.class))).thenReturn(paymentService);

        Payment savedPayment = accountancyService.savePayment(paymentToBeSaved);

        assertThat(savedPayment).isEqualToComparingFieldByField(expectedSavedPayment);

    }



}
