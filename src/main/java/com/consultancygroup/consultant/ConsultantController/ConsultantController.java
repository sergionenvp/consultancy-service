package com.consultancygroup.consultant.ConsultantController;
import com.consultancygroup.consultant.ConsultantService.ConsultantService;
import com.consultancygroup.consultant.Model.Consultant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
@RestController
public class ConsultantController {
    @Autowired
    private ConsultantService consultantService;
    @PostMapping("/consultant")
    @ResponseStatus(HttpStatus.CREATED)
    public Consultant saveConsultant(@Valid @RequestBody Consultant consultant) {
        return  consultantService.saveConsultant(consultant);
    }
    @GetMapping("/consultant/{id}")
    public Consultant getConsultantById(@PathVariable("id") Long consultantId) {
        return  consultantService.findConsultantById(consultantId);
    }
@DeleteMapping("/users/{id}")
    Consultant deleteConsultant(@Valid @PathVariable Long id){
        return consultantService.deleteConsultantById(id);
}
}