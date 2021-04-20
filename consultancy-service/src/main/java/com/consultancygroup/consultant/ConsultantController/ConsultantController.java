package com.consultancygroup.consultant.ConsultantController;
import com.consultancygroup.consultant.ConsultantService.ConsultantService;
import com.consultancygroup.consultant.Model.Consultant;
import com.consultancygroup.consultant.Model.ConsultantResume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.*;
import java.util.LinkedList;
import java.util.List;

@RestController
@Transactional
public class ConsultantController {
    @Autowired
    private ConsultantService consultantService;

    @PostMapping("/consultant")
    @ResponseStatus(HttpStatus.CREATED)
    public Consultant saveConsultant(@Valid @RequestBody Consultant consultant) {
        return  consultantService.saveConsultant(consultant);
    }

    @RequestMapping("/consultant/id/{id}")
    public Consultant getConsultantById(@PathVariable("id") Long consultantId) {
        return  consultantService.findConsultantById(consultantId);
    }


    @GetMapping("/consultant/fullName/{fullName}")
    public List<Consultant> getConsultantByFullName(@PathVariable("fullName") String fullName) {
        return  consultantService.findAllByFullName(fullName);
    }

    @GetMapping("/consultant/consultantResume/{consultantResume}")
    public List<Consultant> getConsultantByConsultantResume(@PathVariable("consultantResume") ConsultantResume consultantResume) {
        return  consultantService.findAllByConsultantResume(consultantResume);
    }

    @GetMapping("/consultant")
    public List<Consultant> getAllConsultants(){
        return  consultantService.findAll();

    }
    @DeleteMapping("/consultant/id/{id}")
        public void deleteById(@PathVariable("id") Long consultantId) {
        consultantService.deleteByConsultantId(consultantId);
    }

    @DeleteMapping("/consultant/delete")
    public void deleteAll() {
        consultantService.deleteAllConsultants();
    }

    @GetMapping("/consultant/ageMinimum/{age}")
    public List<Consultant> getConsultantsOlderThanMinAge(@PathVariable("age") int age) {
        return  consultantService.getConsultantsOlderThanMinAge(age);
    }





    @GetMapping("/consultant/export")
    public void write(){
        List<Consultant> consultants = consultantService.findAll();
        consultantService.export(consultants);


    }



}