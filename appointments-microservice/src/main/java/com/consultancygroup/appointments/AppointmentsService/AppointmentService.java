package com.consultancygroup.appointments.AppointmentsService;

import com.consultancygroup.appointments.AppointmentsRepository.AppointmentRepository;
import com.consultancygroup.appointments.model.Appointment;
import com.consultancygroup.appointments.model.AppointmentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional

public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;


    public Appointment saveAppointment(Appointment appointment) {

        duringAvailableTimes(appointment);
        return appointmentRepository.save(appointment);
    }

    public Appointment findAppByAppNum(Long appNum) {
        return appointmentRepository.findAppointmentByAppointmentNum(appNum);
    }

    public List<Appointment> findAppsByConsultantAndDate(Long consultantId, LocalDate date){
        return appointmentRepository.findAppointmentByConsultantIdAndDate(consultantId,date);
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

    public List<Appointment> findAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Appointment duringAvailableTimes(Appointment appointment){

        LocalDateTime date = appointment.getDate();

        //set business hours from 10 to 5
        LocalDateTime startTime = LocalDateTime.of(2021,1,1,8,00);
        LocalDateTime endTime = LocalDateTime.of(2021,1,1,17,00);

        if (!(date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY)) {
            if (!(startTime.toLocalTime().isBefore( date.toLocalTime() ) && endTime.toLocalTime().isAfter( date.toLocalTime() ))) {
                //Time is not within business hours
                date = date.plusDays(1).with((LocalTime.of(8, 0)));//set to next day

                if(checkDateAvailability(appointment.getConsultantId(),date)){
                    appointment.setDate(date);
                }else{
                    appointment.setDate(date.plusMinutes(30));
                }
            }else if (!(checkDateAvailability(appointment.getConsultantId(),date))){
                //date is within business hours but is already booked
                appointment.setDate(date.plusMinutes(30));
            }

        }else{
            //date is during weekend therefore set to next Monday
            date = date.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
            if(checkDateAvailability(appointment.getConsultantId(),date)){
                appointment.setDate(date);
                appointment.setStatus(AppointmentStatus.AVAILABLE);
            }

        }

        return appointment;
    }


    public boolean checkDateAvailability(Long consultantId,LocalDateTime date) {
        boolean isAvailable = true;
        List<Appointment> consultantAppointmentsList = appointmentRepository.findAllByConsultantId(consultantId);

      //  boolean isSameConsaltant = true;
        boolean isSameDate = true;
        for (int i = 0; i < consultantAppointmentsList.size(); i++) {
           // isSameConsaltant = appointment.getConsultantId().equals(appointmentsList.get(i).getConsultantId());
            isSameDate = date.equals(consultantAppointmentsList.get(i).getDate());

            if (isSameDate) {
                isAvailable = false;
               consultantAppointmentsList.get(i).setStatus(AppointmentStatus.BOOKED);
            }

        }

        return isAvailable;
    }
}
