package com.consultancygroup.appointments.AppointmentsRepository;

import com.consultancygroup.appointments.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Date;
import java.time.LocalDate;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long>{

    public Appointment findAppointmentByAppointmentNum(Long appointmentNumber);

    public List<Appointment> findAppsByConsultantIdAndDate(Long consultantId, LocalDate date);

    public List<Appointment> findAllByConsultantId(Long consultantId);

    public void deleteAll();

}