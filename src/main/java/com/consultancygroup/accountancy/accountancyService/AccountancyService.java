package com.consultancygroup.accountancy.accountancyService;


import com.consultancygroup.accountancy.model.Payment;

import java.util.List;

public interface AccountancyService {

    public Payment savePayment(Payment payment);

    public Payment getPaymentById(Long id);

    public List<Payment> getAllPayments();

    public void deletePaymentById(Long l);

    public void deleteAllPayments();

    public List<Payment> getAllPaymentsById(List<Long> ids);

}
