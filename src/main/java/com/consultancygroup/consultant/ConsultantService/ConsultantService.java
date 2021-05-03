package com.consultancygroup.consultant.ConsultantService;

import com.consultancygroup.consultant.ConsultantRepository.ConsultantRepository;
import com.consultancygroup.consultant.Model.Consultant;
import com.consultancygroup.consultant.Model.ConsultantResume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;




@Service
@Transactional

public class ConsultantService {

    @Autowired
    private ConsultantRepository consultantRepository;

    public Consultant saveConsultant(Consultant consultant) {
        return consultantRepository.save(consultant);
    }

    //tested
    public Consultant findConsultantById(Long consultantId) {
        return consultantRepository.findByConsultantId(consultantId);
    }


//tested
    public List<Consultant> findAllByFullName(String fullName) {
        return consultantRepository.findAllByFullName(fullName);

    }
//tested
    public List<Consultant> findAllByConsultantResume(ConsultantResume consultantResume) {
        return consultantRepository.findAllByConsultantResume(consultantResume);

    }
//tested
    public List<Consultant> findAll() {

        return consultantRepository.findAll();
    }


    public void deleteByConsultantId(Long consultantId) {
         consultantRepository.deleteAllByConsultantId(consultantId);
    }


    public void deleteAllConsultants() {
        consultantRepository.deleteAll();
    }


    public List<Consultant> getConsultantsOlderThanMinAge(int age) {
        return consultantRepository.findAllByAgeAfter(age);
    }






}