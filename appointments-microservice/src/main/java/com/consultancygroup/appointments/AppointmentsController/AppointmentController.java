package com.consultancygroup.appointments.AppointmentsController;

import com.consultancygroup.appointments.AppointmentsService.AppointmentService;
import com.consultancygroup.appointments.model.Appointment;
import com.consultancygroup.appointments.exceptions.AppointmentNumException;
import com.consultancygroup.appointments.serialization.Serialization;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@Transactional
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;

    @Autowired
    ModelMapper modelMapper;

    //implemented in client app
    //done
    @PostMapping("/appointments")
    @ResponseStatus(HttpStatus.CREATED)
    Appointment createAppointment(@Valid @RequestBody Appointment newApp) {

        Appointment appointment = modelMapper.map(newApp, Appointment.class);
        appointment = appointmentService.saveAppointment(appointment);

        return appointment;
    }

    //implemented in client app
    @GetMapping("/appointments/id/{id}")
    public Appointment getAppointmentByAppointmentNum(@PathVariable("id")Long appNum) {
        Appointment app = appointmentService.findAppByAppNum(appNum);
        if (app == null) {
            throw new AppointmentNumException(appNum);
        }
        return app;
    }


    //returns consultant's appointments on particular date
    @GetMapping("/appointments/date/{id}/{date}")
    public List<Appointment> getAppointmentByConsultantAndDate(@PathVariable("id")Long id,@PathVariable("date") @DateTimeFormat(iso= DateTimeFormat.ISO.DATE) LocalDate date) {
        return (appointmentService.findAppsByConsultantAndDate(id,date));
    }

    //implemented in client app
    //returns all the consultant's appointments
    @GetMapping("/appointments/consultant/{id}")
    public List<Appointment> getAllAppointmentsByConsultantId(@PathVariable("id")Long consultantId) {
        return appointmentService.findAllAppointmentsByConsultant(consultantId);
    }

    //implemented
    @PutMapping("/appointments/update/{id}")
    public Appointment updateAppointment(@Valid @RequestBody Appointment appointment, @PathVariable("id")Long appNum) {
        Appointment app = appointmentService.findAppByAppNum(appNum);
        if(app == null)
            throw new AppointmentNumException(appNum);
        else{
            appointment.setAppointmentNum(appNum);
            appointmentService.saveAppointment(appointment);
        }
        return appointment;
    }


    @GetMapping("/appointment/export")
    public void write(){
        List<Appointment> appointments = appointmentService.findAllAppointments();
        Serialization serialization = new Serialization();
        serialization.export(appointments);
    }

    @DeleteMapping("/appointments/id/{id}")
    public void deleteByAppNum(@PathVariable("id") Long appNum) {
        appointmentService.deleteAppointmentByAppNum(appNum);
    }

    @DeleteMapping("/appointments/delete/all")
    public void deleteAllAppointments() {
        appointmentService.deleteAllAppointments();
    }


}
