package com.consultancygroup.consultant.ConsultantService;

import com.consultancygroup.consultant.ConsultantRepository.ConsultantRepository;
import com.consultancygroup.consultant.Model.Consultant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConsultantService {

    @Autowired
    private ConsultantRepository consultantRepository;

    public Consultant saveConsultant(Consultant consultant) {
        return consultantRepository.save(consultant);
    }

    public Consultant findConsultantById(Long departmentId) {
        return consultantRepository.findByConsultantId(departmentId);
    }


    public Consultant deleteConsultantById(Long consultantId) {
        return consultantRepository.deleteConsultantByConsultantId(consultantId);
    }
}