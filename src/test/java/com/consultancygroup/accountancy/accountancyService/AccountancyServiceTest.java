package com.consultancygroup.accountancy.accountancyService;

import com.consultancygroup.accountancy.accountancyRepository.AccountancyRepository;
import com.consultancygroup.accountancy.model.ConsultantResume;
import com.consultancygroup.accountancy.model.Payment;
import com.consultancygroup.accountancy.model.Worker;
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

    private Worker worker1;
    private Worker worker2;
    private Worker worker3;

    @BeforeEach
    private void setUp() {
        worker1 = new Worker(1L,3, ConsultantResume.EXECUTIVE);
        worker2 = new Worker(2L,0, ConsultantResume.EXECUTIVE);
        worker3 = new Worker(3L,0, ConsultantResume.EXECUTIVE);
    }


    @Test
    public void testSaveValidPayment() {
        Payment paymentToBeSaved = new Payment(1L,"Sergio", "12212", null, "334", worker1.getId(), 3);
        Payment expectedSavedPayment = new Payment(1L, "Sergio", "12212", null, "334", worker1.getId(),3);

        Payment paymentService = new Payment(1L, "Sergio", "12212", null, "334", worker1.getId(), 3);
        when(accountancyService.savePayment(any(Payment.class))).thenReturn(paymentService);

        Payment savedPayment = accountancyService.savePayment(paymentToBeSaved);

        assertThat(savedPayment).isEqualToComparingFieldByField(expectedSavedPayment);
    }

   /* @Test
    public void testGetPaymentById() {
        Payment payment = new Payment(1L,"Sergio", "12212", null, "334", worker1.getId(), 3);

        Payment servicePayment = new Payment(1L,"Sergio", "12212", null, "334", worker1.getId(), 3);
        when(accountancyRepository.findById(any(Long.class)).get()).thenReturn(servicePayment);
        //I think this does not work because the load is eager, findById(). With getOne works.
        Payment gottenPayment = accountancyService.getPaymentById(1L);

        assertThat(gottenPayment).isEqualToComparingFieldByField(payment);
    }*/

    @Test
    public void testGetAllPayments() {
        Payment payment1 = new Payment(1L, "Sergio", "12212", null, "334", worker1.getId(),3);
        Payment payment2 = new Payment(2L, "Ana", "3456456", null, "334", worker2.getId(), 3);
        Payment payment3 = new Payment(3L, "Sara", "3456", null, "334", worker3.getId(),3);
        List<Payment> payments = new ArrayList<Payment>(){{ add(payment1); add(payment2); add(payment3); }};

        List<Payment> servicePayments = new ArrayList<Payment>(){{ add(payment1); add(payment2); add(payment3); }};
        when(accountancyService.getAllPayments()).thenReturn(servicePayments);

        List<Payment> returnedPayments = accountancyService.getAllPayments();

        for(int i = 0; i < returnedPayments.size(); i++)
            assertThat(payments.get(i)).isEqualToComparingFieldByField(returnedPayments.get(i));
    }

    @Test
    public void testGetAllPaymentsById() {
        Payment payment1 = new Payment(1L, "Sergio", "12212", null, "334", worker1.getId(),3);
        Payment payment2 = new Payment(2L, "Ana", "3456456", null, "334", worker2.getId(), 3);
        Payment payment3 = new Payment(3L, "Sara", "3456", null, "334", worker3.getId(),3);
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
    void testDeleteConsultantByValidId() {
        accountancyService.deletePaymentById(1L);

        verify(accountancyRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteAll() {
        accountancyService.deleteAllPayments();

        verify(accountancyRepository, times(1)).deleteAll();
    }


}
