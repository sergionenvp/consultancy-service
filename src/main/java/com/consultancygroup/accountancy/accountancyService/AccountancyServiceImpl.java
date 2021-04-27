package com.consultancygroup.accountancy.accountancyService;


import com.consultancygroup.accountancy.accountancyRepository.AccountancyRepository;
import com.consultancygroup.accountancy.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AccountancyServiceImpl implements AccountancyService{

    @Autowired
    private AccountancyRepository accountancyRepository;

    @Override
    public Payment savePayment(Payment payment) {
        accountancyRepository.save(payment);
        return payment;
    }

    @Override
    public Payment getPaymentById(Long id) {
        return accountancyRepository.getOne(id);
    }

    @Override
    public List<Payment> getAllPayments() {
        return accountancyRepository.findAll();
    }

    @Override
    public void deletePaymentById(Long id) {
        accountancyRepository.deleteById(id);
    }
}
