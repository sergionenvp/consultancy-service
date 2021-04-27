package com.consultancygroup.accountancy.accountancyService;


import com.consultancygroup.accountancy.model.Payment;

import java.util.List;

public interface AccountancyService {

    Payment savePayment(Payment payment);

    Payment getPaymentById(Long id);

    List<Payment> getAllPayments();

    void deletePaymentById(Long l);

}
