package com.consultancygroup.accountancy.accountancyService;

import com.consultancygroup.accountancy.accountancyRepository.AccountancyRepository;
import com.consultancygroup.accountancy.model.ConsultantResume;
import com.consultancygroup.accountancy.model.Payment;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.jdbc.Work;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class AccountancyServiceTest {

    @MockBean
    AccountancyRepository accountancyRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    AccountancyService accountancyService;

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
    public void testSaveValidPayment() {
        Payment paymentToBeSaved = new Payment(1L,"Sergio", "12212", null, "334", 1L, ConsultantResume.JUNIOR,  3);
        Payment expectedSavedPayment = new Payment(1L, "Sergio", "12212", null, "334", 1L, ConsultantResume.JUNIOR, 3);

        Payment paymentService = new Payment(1L, "Sergio", "12212", null, "334", 1L, ConsultantResume.JUNIOR, 3);
        when(accountancyRepository.save(paymentToBeSaved)).thenReturn(paymentService);

        Payment savedPayment = accountancyService.savePayment(paymentToBeSaved);

        assertThat(savedPayment).isEqualToIgnoringGivenFields(expectedSavedPayment, "workerMoney", "commissionCompany");
    }

    @Test
    public void testGetPaymentById() {
        when(accountancyRepository.findById(1L)).thenReturn(java.util.Optional.of(payment1));
        Payment returnedPayment = accountancyService.getPaymentById(1L);
        assertEquals(payment1, returnedPayment);
    }

    @Test
    public void testGetAllPayments() {
        List<Payment> payments = new ArrayList<Payment>(){{ add(payment1); add(payment2); add(payment3); }};

        List<Payment> servicePayments = new ArrayList<Payment>(){{ add(payment1); add(payment2); add(payment3); }};
        when(accountancyService.getAllPayments()).thenReturn(servicePayments);

        List<Payment> returnedPayments = accountancyService.getAllPayments();

        for(int i = 0; i < returnedPayments.size(); i++)
            assertThat(payments.get(i)).isEqualToComparingFieldByField(returnedPayments.get(i));
    }

    @Test
    public void testGetAllPaymentsById() {
        List<Payment> payments = new ArrayList<Payment>(){{ add(payment1); add(payment2); add(payment3); }};

        List<Long> ids = new ArrayList<Long>();
        ids.add(payment1.getId());
        ids.add(payment3.getId());
        List<Payment> servicePayments = new ArrayList<Payment>(){{ add(payment1); add(payment3); }};
        when(accountancyService.getAllPaymentsById(ids)).thenReturn(servicePayments);

        List<Payment> returnedPayments = accountancyService.getAllPaymentsById(ids);

        assertThat(payments.get(0)).isEqualToComparingFieldByField(returnedPayments.get(0));
        assertThat(payments.get(2)).isEqualToComparingFieldByField(returnedPayments.get(1));
    }

    @Test
    public void testDeleteConsultantByValidId() {
        accountancyService.deletePaymentById(1L);

        verify(accountancyRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteAll() {
        accountancyService.deleteAllPayments();

        verify(accountancyRepository, times(1)).deleteAll();
    }

    @Test
    public void testGetWorkerMoneyByPaymentId() {
        Payment payment = accountancyService.savePayment(payment1);
        double profit = payment.getWorkerMoney();

        double d = 2.7;
        when(accountancyRepository.findProfitByPaymentId(1L)).thenReturn(d);

        double returnedMoney = accountancyService.getWorkerProfitByPaymentId(1L);

        assertEquals(profit, returnedMoney);
    }

    @Test
    public void testGetWorkerMoneyByWorkerId() {
        Payment payment = accountancyService.savePayment(payment1);
        double profit = payment.getWorkerMoney();

        double d = 2.7;
        when(accountancyRepository.findProfitByWorkerId(1L)).thenReturn(d);

        double returnedMoney = accountancyService.getWorkerProfitByWorkerId(1L);

        assertEquals(profit, returnedMoney);
    }

    @Test
    public void testGetPaymentByWorkerId() {
        when(accountancyRepository.findPaymentWorkerId(any(Long.class))).thenReturn(payment1);
        Payment returnedPayment = accountancyService.getPaymentByWorkerId(1L);
        assertEquals(payment1, returnedPayment);
    }


}
