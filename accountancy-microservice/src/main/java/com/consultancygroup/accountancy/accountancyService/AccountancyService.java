package com.consultancygroup.accountancy.accountancyService;


import com.consultancygroup.accountancy.model.Payment;

import java.util.List;

public interface AccountancyService {

    public Payment savePayment(Payment payment);

    public Payment getPaymentById(Long l);

    public List<Payment> getAllPayments();

    public void deletePaymentById(Long l);

    public void deleteAllPayments();

    public List<Payment> getAllPaymentsById(List<Long> ids);

    public double getWorkerProfitByPaymentId(Long l);

    public double getWorkerBalanceByWorkerId(Long l);

    public List<Payment> getPaymentsByWorkerId(Long l);

    public double getCompanyBalance();

    public double getCompanyMoneyById(long l);
}
