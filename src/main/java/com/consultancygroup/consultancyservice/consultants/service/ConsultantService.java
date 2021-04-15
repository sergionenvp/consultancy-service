package com.consultancygroup.consultancyservice.consultants.service;


import com.consultancygroup.consultancyservice.consultants.entity.Consultant;
import com.consultancygroup.consultancyservice.consultants.repository.ConsultantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultantService {

@Autowired
private ConsultantRepository consultantRepository;

    public Consultant saveConsultant(Consultant consultant) {

        return  consultantRepository.save(consultant);
    }
}
