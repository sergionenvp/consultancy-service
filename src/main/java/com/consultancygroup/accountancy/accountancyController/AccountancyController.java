package com.consultancygroup.accountancy.accountancyController;


import com.consultancygroup.accountancy.accountancyService.AccountancyService;
import com.consultancygroup.accountancy.model.Payment;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountancyController {

    @Autowired
    AccountancyService accountancyService;

    @Autowired
    ModelMapper modelMapper;

    Payment createPayment(){
        return null;
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
