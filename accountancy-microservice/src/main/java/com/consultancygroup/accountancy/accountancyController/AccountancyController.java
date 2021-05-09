package com.consultancygroup.accountancy.accountancyController;


import com.consultancygroup.accountancy.accountancyService.AccountancyService;
import com.consultancygroup.accountancy.model.Payment;
import com.consultancygroup.accountancy.exceptions.PaymentIdNotFoundException;
import com.consultancygroup.accountancy.serialization.Serialization;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Transactional
public class AccountancyController {

    @Autowired
    AccountancyService accountancyService;

    @Autowired
    ModelMapper modelMapper;

    //done
    @PostMapping("/payments")
    @ResponseStatus(HttpStatus.CREATED)
    Payment newPayment(@Valid @RequestBody Payment paymentRequest) {
        Payment createdPayment = modelMapper.map(paymentRequest, Payment.class);
        createdPayment = accountancyService.savePayment(createdPayment);
        return createdPayment;
    }

    //done
    @DeleteMapping("/payments/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    void deletePaymentById(@PathVariable Long id) {
        accountancyService.deletePaymentById(id);
    }

    //done
    @GetMapping("/payments/all")
    @ResponseStatus(HttpStatus.OK)
    List<Payment> getAllPayments() {
        return accountancyService.getAllPayments();
    }

    //done
    @GetMapping("/payments/all/{ids}")
    @ResponseStatus(HttpStatus.OK)
    List<Payment> getAllPaymentsById(@PathVariable("ids") List<Long> ids) {
        return accountancyService.getAllPaymentsById(ids);
    }


    /*
        returns total profit made by company
     */

    //done
    @GetMapping("/payments/company/balance")
    @ResponseStatus(HttpStatus.OK)
    double getCompanyBalance() {
        return accountancyService.getCompanyBalance();
    }

    //done
    @GetMapping("/payments/company/balance/payment/{id}")
    @ResponseStatus(HttpStatus.OK)
    double getCompanyMoneyByPaymentId(@PathVariable Long id) {
        return accountancyService.getCompanyMoneyById(id);
    }

    //done
    @GetMapping("/payments/{id}")
    @ResponseStatus(HttpStatus.OK)
    Payment getPaymentById(@PathVariable("id") Long id) {
        Payment payment = accountancyService.getPaymentById(id);
        if (payment == null)
            throw new PaymentIdNotFoundException(id);
        return payment;
    }

    //done
    @GetMapping("/payments/worker/{id}")
    @ResponseStatus(HttpStatus.OK)
    List<Payment> getPaymentsByWorkerId(@PathVariable("id") Long id) {
        return accountancyService.getPaymentsByWorkerId(id);
    }

    //done
    @GetMapping("/payments/salary/payment/{id}")
    @ResponseStatus(HttpStatus.OK)
    double getWorkerProfitByPaymentId(@PathVariable("id") Long id) {
        return accountancyService.getWorkerProfitByPaymentId(id);
    }

    //done
    @GetMapping("/payments/salary/worker/{id}")
    @ResponseStatus(HttpStatus.OK)
    double getWorkerBalanceByWorkerId(@PathVariable("id") Long id) {
        return accountancyService.getWorkerBalanceByWorkerId(id);
    }

    //done
    @PutMapping("/payments/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    Payment changePayment(@Valid @RequestBody Payment payment, @PathVariable("id") Long id) {
        Payment actualPayment = accountancyService.getPaymentById(id);
        if(actualPayment == null)
            throw new PaymentIdNotFoundException(id);
        else{
            payment.setId(id);
            Payment newPayment = accountancyService.savePayment(payment);
            return newPayment;
        }
    }
    //done
    @DeleteMapping("/payments/delete/all")
    void deleteAllPayments(){
        accountancyService.deleteAllPayments();
    }

    //
    @GetMapping("/payments/export")
    public void write(){
        List<Payment> payments = accountancyService.getAllPayments();
        Serialization serialization = new Serialization();
        serialization.export(payments);
    }
}
