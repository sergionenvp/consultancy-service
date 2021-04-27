package com.consultancygroup.consultant.ConsultantController;
import com.consultancygroup.consultant.ConsultantService.ConsultantService;
import com.consultancygroup.consultant.Exceptions.ConsultantIdNotFoundException;
import com.consultancygroup.consultant.Exceptions.ConsultantNameNotFoundException;
import com.consultancygroup.consultant.Exceptions.ConsultantResumeNotFoundException;
import com.consultancygroup.consultant.Model.Consultant;
import com.consultancygroup.consultant.Model.ConsultantResume;
import com.consultancygroup.consultant.Serialisation.Serialisation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.LinkedList;
import java.util.List;
@RestController
@Transactional
public class ConsultantController {
    @Autowired
    private ConsultantService consultantService;

    //tested
    //
    @PostMapping("/consultant")
    @ResponseStatus(HttpStatus.CREATED)
    public Consultant saveConsultant(@Valid @RequestBody Consultant consultant) {
        return  consultantService.saveConsultant(consultant);
    }
    //tested found and not found
    @GetMapping("/consultant/id/{id}")
    public Consultant getConsultantById(@PathVariable("id") Long consultantId) {
        Consultant consultant = consultantService.findConsultantById(consultantId);
        if (consultant == null) {
            throw new ConsultantIdNotFoundException(consultantId);
        }
        return consultant;
    }
    // tested
    @GetMapping("/consultant/fullName/{fullName}")
    public List<Consultant> getConsultantByFullName(@PathVariable("fullName") String fullName) {
        List<Consultant> consultants = consultantService.findAllByFullName(fullName);
        if (consultants.size()==0) {
            throw new ConsultantNameNotFoundException(fullName);
        }
        return   consultants; }
    @GetMapping("/consultant/consultantResume/{consultantResume}")
    public List<Consultant> getConsultantByConsultantResume(@PathVariable("consultantResume") ConsultantResume consultantResume) {
        List<Consultant> consultants = consultantService.findAllByConsultantResume(consultantResume);
        if(consultants.size()==0){
            throw new ConsultantResumeNotFoundException(consultantResume);
        }
        return  consultants;
    }

    @GetMapping("/consultant")
    public List<Consultant> getAllConsultants(){
        return  consultantService.findAll();
    }
    //tested
    @DeleteMapping("/consultant/id/{id}")
        public void deleteById(@PathVariable("id") Long consultantId) {
        consultantService.deleteByConsultantId(consultantId);
    }
    @DeleteMapping("/consultant/deleteAll")
    public void deleteAll() {
        consultantService.deleteAllConsultants();
    }
    //tested with constraints
    @GetMapping("/consultant/ageMinimum/{age}")
    public List<Consultant> getConsultantsOlderThanMinAge(@PathVariable("age") int age) {
        return  consultantService.getConsultantsOlderThanMinAge(age);
    }
    @GetMapping("/consultant/export")
    public void write(){
        List<Consultant> consultants = consultantService.findAll();
        Serialisation serialisation = new Serialisation();
        serialisation.export(consultants);
    }

}