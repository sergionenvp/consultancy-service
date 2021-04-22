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

    public Consultant findConsultantById(Long consultantId) {
        return consultantRepository.findByConsultantId(consultantId);
    }



    public List<Consultant> findAllByFullName(String fullName) {
        return consultantRepository.findAllByFullName(fullName);

    }

    public List<Consultant> findAllByConsultantResume(ConsultantResume consultantResume) {
        return consultantRepository.findAllByConsultantResume(consultantResume);

    }

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

    public void export(List<Consultant> consultants) {
        try
        {
            FileOutputStream fos = new FileOutputStream("consultant");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(consultants);
            oos.close();
            fos.close();
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
    }

    public List<Consultant> importConsultant() {
        List<Consultant> consultants = new LinkedList<Consultant>();
        try {
            FileInputStream in = new FileInputStream("consultant");
            ObjectInputStream ois = new ObjectInputStream(in);
            consultants = (List<Consultant>) (ois.readObject());
        } catch (Exception e) {
            System.out.println("Problem serializing: " + e);
        }
        return  consultants;

    }


}