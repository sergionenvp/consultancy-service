package com.consultancygroup.accountancy.accountancyService;


import com.consultancygroup.accountancy.accountancyRepository.AccountancyRepository;
import com.consultancygroup.accountancy.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountancyServiceImpl implements AccountancyService{

    @Autowired
    private AccountancyRepository accountancyRepository;

    @Override
    public Payment savePayment(Payment payment) {
        accountancyRepository.save(payment);
        return payment;
    }
}
