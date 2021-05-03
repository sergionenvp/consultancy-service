package com.consultancygroup.consultant.Exceptions;

import com.consultancygroup.consultant.Model.Consultant;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ConsultantNameNotFoundException extends RuntimeException {

    public ConsultantNameNotFoundException(String name) {
        super("Consultant with name:" + name + "' not found.");
    }

}