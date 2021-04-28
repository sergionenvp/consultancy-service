package com.consultancygroup.accountancy.accountancyController;


import com.consultancygroup.accountancy.accountancyService.AccountancyService;
import com.consultancygroup.accountancy.model.Payment;
import com.consultancygroup.exceptions.PaymentIdNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@Transactional
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

    @DeleteMapping("/payments/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    void deletePaymentById(@PathVariable Long id){
        accountancyService.savePayment(new Payment(1L,"Sergio", "12212", null, "334", 0));
        accountancyService.deletePaymentById(id);
    }

    @GetMapping("/payments/all")
    @ResponseStatus(HttpStatus.OK)
    List<Payment> getAllPayments(){
        //accountancyService.savePayment(new Payment(1L,"Sergio", "12212", null, "334", 0));
        //accountancyService.savePayment(new Payment(2L,"Sara", "12212", null, "334", 0));
        //accountancyService.savePayment(new Payment(3L,"Ana", "w43634", null, "334", 0));
        return accountancyService.getAllPayments();
    }

    @GetMapping("/payments/{id}")
    @ResponseStatus(HttpStatus.OK)
    Payment getPaymentById(@PathVariable("id") Long id){
        //accountancyService.savePayment(new Payment(1L,"Sergio", "12212", null, "334", 0));
        //accountancyService.savePayment(new Payment(2L,"Sergio", "12212", null, "334", 0));
        //accountancyService.savePayment(new Payment(3L,"Ana", "12212", null, "334", 0));
        Payment payment = accountancyService.getPaymentById(id);
        if(payment == null)
            throw new PaymentIdNotFoundException(id);
        return payment;
    }


    Payment changePayment(Payment payment){
        return null;
    }


}
