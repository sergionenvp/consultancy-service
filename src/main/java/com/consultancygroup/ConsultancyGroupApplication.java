package com.consultancygroup;

import com.consultancygroup.appointments.AppointmentsRepository.AppointmentRepository;
import com.consultancygroup.appointments.model.Appointment;
import com.consultancygroup.appointments.serialization.Serialization;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class ConsultancyGroupApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsultancyGroupApplication.class, args);

    }

    @Bean
    public CommandLineRunner demo(AppointmentRepository appointmentRepository) {
        return (args) -> {
            Serialization serialization = new Serialization();
            List<Appointment> appointments = serialization.importAppointments();
            appointmentRepository.saveAll(appointments);
        };
    }

}
