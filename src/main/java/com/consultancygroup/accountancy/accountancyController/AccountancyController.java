package com.consultancygroup.accountancy.accountancyController;


import com.consultancygroup.accountancy.accountancyService.AccountancyService;
import com.consultancygroup.accountancy.model.Payment;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AccountancyController {

    @Autowired
    AccountancyService accountancyService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping("/payments")
    @ResponseStatus(HttpStatus.CREATED)
    Payment newPayment(@Valid @RequestBody Payment paymentRequest){
        Payment createdPayment = modelMapper.map(paymentRequest, Payment.class);
        createdPayment = accountancyService.savePayment(createdPayment);
        return createdPayment;
    }

    void cancelPaymentById(Long id){

    }

    List<Payment> showPayments(){
        return null;
    }

    Payment showSpecificPaymentById(Long id){
        return null;
    }


    Payment changePayment(Payment payment){
        return null;
    }


}
