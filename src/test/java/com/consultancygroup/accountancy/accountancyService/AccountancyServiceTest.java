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

import java.util.ArrayList;
import java.util.List;

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
    public void testSaveValidPayment(){
        Payment paymentToBeSaved = new Payment(1L,"Sergio", "12212", null, "334", 3);
        Payment expectedSavedPayment = new Payment(1L, "Sergio", "12212", null, "334", 3);

        Payment paymentService = new Payment(1L, "Sergio", "12212", null, "334", 3);
        when(accountancyService.savePayment(any(Payment.class))).thenReturn(paymentService);

        Payment savedPayment = accountancyService.savePayment(paymentToBeSaved);

        assertThat(savedPayment).isEqualToComparingFieldByField(expectedSavedPayment);

    }

    @Test
    public void testGetPaymentById(){
        Payment payment = new Payment(1L,"Sergio", "12212", null, "334", 3);

        Payment servicePayment = new Payment(1L,"Sergio", "12212", null, "334", 3);
        when(accountancyService.getPaymentById(any(Long.class))).thenReturn(servicePayment);

        Payment gottenPayment = accountancyService.getPaymentById(1L);

        assertThat(gottenPayment).isEqualToComparingFieldByField(payment);
    }

    @Test
    public void testGetAllPayments(){
        Payment payment1 = new Payment(1L,"Sergio", "12212", null, "334", 3);
        Payment payment2 = new Payment(1L,"Ana", "3456456", null, "334", 3);
        Payment payment3 = new Payment(1L,"Sara", "3456", null, "334", 3);
        List<Payment> payments = new ArrayList<Payment>(){{ add(payment1); add(payment2); add(payment3); }};

        List<Payment> servicePayments = new ArrayList<Payment>(){{ add(payment1); add(payment2); add(payment3); }};

        when(accountancyService.getAllPayments()).thenReturn(servicePayments);

        List<Payment> returnedPayments = accountancyService.getAllPayments();

        for(int i = 0; i < returnedPayments.size(); i++)
            assertThat(payments.get(i)).isEqualToComparingFieldByField(returnedPayments.get(i));
    }




}
