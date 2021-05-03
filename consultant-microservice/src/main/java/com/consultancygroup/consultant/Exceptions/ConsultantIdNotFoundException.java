package com.consultancygroup.consultant.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ConsultantIdNotFoundException extends RuntimeException {

    public ConsultantIdNotFoundException(Long id) {
        super("Consultant with ID:" + id + "' not found.");
    }

}