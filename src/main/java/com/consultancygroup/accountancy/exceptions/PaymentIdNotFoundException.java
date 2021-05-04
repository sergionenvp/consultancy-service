package com.consultancygroup.accountancy.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PaymentIdNotFoundException extends RuntimeException {

    public PaymentIdNotFoundException(Long id) {
        super("Payment with id: " + id + ", sssnot found.");
    }
}
