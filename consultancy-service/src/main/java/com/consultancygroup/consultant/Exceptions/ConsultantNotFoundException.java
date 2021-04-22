package com.consultancygroup.consultant.Exceptions;

import com.consultancygroup.consultant.Model.Consultant;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ConsultantNotFoundException extends RuntimeException {

    public ConsultantNotFoundException(Long id) {
        super("Consultant with ID '" + id + "' not found.");
    }
}