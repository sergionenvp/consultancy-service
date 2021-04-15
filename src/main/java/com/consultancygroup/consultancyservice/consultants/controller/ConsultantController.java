package com.consultancygroup.consultancyservice.consultants.controller;

import com.consultancygroup.consultancyservice.consultants.entity.Consultant;
import com.consultancygroup.consultancyservice.consultants.repository.ConsultantRepository;
import com.consultancygroup.consultancyservice.consultants.service.ConsultantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsultantController {


    @Autowired
    private ConsultantService consultantService;

    @PostMapping("/consultants")
    public Consultant saveConsultant(Consultant consultant){
        return consultantService.saveConsultant(consultant);

    }
}
