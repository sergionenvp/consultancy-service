package com.consultancygroup.accountancy.accountancyService;


import com.consultancygroup.accountancy.accountancyRepository.AccountancyRepository;
import com.consultancygroup.accountancy.model.ConsultantResume;
import com.consultancygroup.accountancy.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AccountancyServiceImpl implements AccountancyService {

    @Autowired
    private AccountancyRepository accountancyRepository;

    @Override
    public Payment savePayment(Payment payment) {


        if(payment.getResume().equals(ConsultantResume.JUNIOR)) {
            payment.setCommissionCompany(0.15*payment.getPrice());
            payment.setWorkerMoney(payment.getPrice() - payment.getCommissionCompany());

        } else if (payment.getResume().equals(ConsultantResume.EXECUTIVE)) {
            payment.setCommissionCompany(0.10*payment.getPrice());
            payment.setWorkerMoney(payment.getPrice() - payment.getCommissionCompany());

        } else if (payment.getResume().equals(ConsultantResume.SENIOR)) {
            payment.setCommissionCompany(0.05*payment.getPrice());
            payment.setWorkerMoney(payment.getPrice() - payment.getCommissionCompany());
        }
        accountancyRepository.save(payment);

        return payment;
    }

    @Override
    public Payment getPaymentById(Long id) { return accountancyRepository.findById(id).get(); }

    @Override
    public List<Payment> getAllPayments() {
        return accountancyRepository.findAll();
    }

    @Override
    public void deletePaymentById(Long id) {
        accountancyRepository.deleteById(id);
    }

    @Override
    public void deleteAllPayments() { accountancyRepository.deleteAll(); }

    @Override
    public List<Payment> getAllPaymentsById(List<Long> ids) { return accountancyRepository.findAllById(ids); }

    @Override
    public double getWorkerProfitByPaymentId(Long l) {
        double profit = accountancyRepository.findProfitByPaymentId(l);
        return profit;
    }

    @Override
    public double getWorkerProfitByWorkerId(Long l) {
        double profit = accountancyRepository.findProfitByWorkerId(l);
        return profit;
    }

    @Override
    public Payment getPaymentByWorkerId(Long l) {
        return accountancyRepository.findPaymentWorkerId(l);
    }
}
