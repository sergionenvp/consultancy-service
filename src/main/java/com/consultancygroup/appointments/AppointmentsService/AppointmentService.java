package com.consultancygroup.appointments.AppointmentsService;

import com.consultancygroup.appointments.AppointmentsRepository.AppointmentRepository;
import com.consultancygroup.appointments.model.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.time.LocalDate;
import java.util.List;
@Service
@Transactional

public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;
    List<Appointment> appointments;

    public Appointment saveAppointment(Appointment appointment) {

        //implement booked consultant constraint and  business hour constraint
       /* for(int i = 0; i < appointments.size(); i++){
            if(appointment.getConsultantId().equals(appointments.get(i).getConsultantId())){
            }
        }*/
        return appointmentRepository.save(appointment);
    }

    public Appointment findAppByAppNum(Long appNum) {
        return appointmentRepository.findAppointmentByAppointmentNum(appNum);
    }

    public List<Appointment> findAppsByConsultantAndDate(Long consultantId, LocalDate date){
        return appointmentRepository.findAppsByConsultantIdAndDate(consultantId,date);
    }

    public List<Appointment> findAllAppointmentsByConsultant(Long consultantId) {
        return appointmentRepository.findAllByConsultantId(consultantId);
    }

    public void deleteAppointmentByAppNum(Long appNum) {
        appointmentRepository.deleteById(appNum);
    }

    public void deleteAllAppointments() {
        appointmentRepository.deleteAll();
    }



}
