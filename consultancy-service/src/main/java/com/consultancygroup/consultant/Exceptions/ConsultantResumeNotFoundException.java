package com.consultancygroup.consultant.Exceptions;

import com.consultancygroup.consultant.Model.ConsultantResume;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ConsultantResumeNotFoundException extends RuntimeException {

    public ConsultantResumeNotFoundException(ConsultantResume consultantResume) {
        super("Consultant with resume:" + consultantResume.toString() + "' not found.");
    }

}