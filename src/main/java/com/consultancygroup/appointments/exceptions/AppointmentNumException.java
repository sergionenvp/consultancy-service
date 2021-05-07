package com.consultancygroup.appointments.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class AppointmentNumException  extends RuntimeException {

    public AppointmentNumException(Long appNum) {
        super("Appointment Number:" + appNum + "' not found.");
    }

}

