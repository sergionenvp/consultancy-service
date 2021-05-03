package com.consultancygroup.consultant.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public class ConsultantOlderThanSomeMinAgeNotFoundException extends RuntimeException {

        public ConsultantOlderThanSomeMinAgeNotFoundException(int age) {
            super("Consultant older than " + age + "' not found.");
        }

    }

