package com.consultancygroup.appointments.AppointmentsRepository;

import com.consultancygroup.appointments.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.FutureOrPresent;
import java.time.LocalDate;
import java.util.List;
import java.util.Date;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long>{

    public Appointment findAppointmentByAppointmentNum(Long appointmentNumber);

   @Query("Select a from Appointment a where a.date = date")
    public List<Appointment> findAppointmentByConsultantIdAndDate(Long id,LocalDate date);

    public List<Appointment> findAllByConsultantId(Long consultantId);

    public void deleteAll();


}