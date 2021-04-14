package com.consultancies.consultants.controller;

import com.consultancies.consultants.model.ConsultantRequest;
import com.consultancies.consultants.model.ConsultantResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class JavaTestController {
    @Autowired
    TestRestTemplate testRestTemplate;

}
