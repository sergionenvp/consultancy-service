package com.consultancygroup.appointments.appointmentService;

import com.consultancygroup.appointments.AppointmentsRepository.AppointmentRepository;
import com.consultancygroup.appointments.exceptions.AppointmentNumException;
import com.consultancygroup.appointments.model.Appointment;
import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.Test;

import com.consultancygroup.appointments.AppointmentsService.AppointmentService;
import com.consultancygroup.appointments.model.Appointment;
import com.consultancygroup.appointments.model.AppointmentStatus;
import com.consultancygroup.appointments.model.ConsultantResume;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.internal.bytebuddy.dynamic.DynamicType;
import org.h2.schema.Constant;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONString;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

import java.util.*;
import java.text.*;
import java.time.LocalDateTime;
import java.text.ParseException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")

public class AppointmentServiceTest {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    AppointmentService appointmentService;

    @MockBean
    AppointmentRepository appointmentRepository;

    Appointment appointment1;
    Appointment appointment2;


    List<Appointment> appointmentsList = new ArrayList<>();

    @BeforeEach
    public void setup() {
        appointment1 = new Appointment(1L,2L,123456L,79123456,LocalDateTime.of(2021,5,10,10,00),100.0, AppointmentStatus.AVAILABLE,ConsultantResume.JUNIOR);
        appointment2 = new Appointment(11L,21L,123457L,79123457,LocalDateTime.of(2021,5,11,10,00),200.0, AppointmentStatus.AVAILABLE,ConsultantResume.EXECUTIVE);

        appointmentsList.add(appointment1);
        appointmentsList.add(appointment2);

    }

    @Test
    public void testSaveAppointment() throws Exception{

        Appointment appointment = new Appointment(1L,2L,123456L,79123456,LocalDateTime.of(2021,5,10,10,00),100.0, AppointmentStatus.AVAILABLE,ConsultantResume.JUNIOR);
        Appointment expectedApp = new Appointment(1L,2L,123456L,79123456,LocalDateTime.of(2021,5,10,10,00),100.0, AppointmentStatus.AVAILABLE,ConsultantResume.JUNIOR);

        Appointment appService = new Appointment(1L,2L,123456L,79123456,LocalDateTime.of(2021,5,10,10,00),100.0, AppointmentStatus.AVAILABLE,ConsultantResume.JUNIOR);
        when(appointmentRepository.save(appointment)).thenReturn(appService);

        Appointment savedApp = appointmentService.saveAppointment(appointment);

        Assertions.assertEquals(savedApp,expectedApp);
    }

    @Test
    public void testFindAppByAppNum() throws Exception{

        when(appointmentRepository.findAppointmentByAppointmentNum(1L)).thenReturn(appointment1);
        Appointment requestedApp = appointmentService.findAppByAppNum(1L);
        Assertions.assertEquals(appointment1, requestedApp);
    }

    @Test
    public void testFindAppByConsultantandDate() throws Exception{
        LocalDateTime date = LocalDateTime.of(2021,5,10,10,00);


        Appointment appointment = new Appointment(1L,3L,789101L,99443322,LocalDateTime.of(2021,5,10,10,30),100.0, AppointmentStatus.AVAILABLE,ConsultantResume.JUNIOR);
        appointmentsList.add(appointment);

        List<Appointment> consultantAppList = new ArrayList<>();
        consultantAppList.add(appointment);
        consultantAppList.add(appointment1);
        
        //made changes
        when(appointmentRepository.findAppointmentByConsultantIdAndDate(1L,date.toLocalDate())).thenReturn(consultantAppList);
        List<Appointment> requestedApp = appointmentService.findAppsByConsultantAndDate(1L,date.toLocalDate());
        Assertions.assertEquals(consultantAppList, requestedApp);

    }

    @Test
    public void testFindAllAppByConsultantId() throws Exception{

        List<Appointment> consultantApp = new ArrayList<>();
        Appointment appointment = new Appointment(1L,3L,789101L,99443322,LocalDateTime.of(2021,5,10,10,30),100.0, AppointmentStatus.AVAILABLE,ConsultantResume.JUNIOR);
        consultantApp.add(appointment);
        consultantApp.add(appointment1);

        when(appointmentRepository.findAllByConsultantId(1L)).thenReturn(consultantApp);
        List<Appointment> requestedApp = appointmentService.findAllAppointmentsByConsultant(1L);
        Assertions.assertEquals(consultantApp, requestedApp);

    }

    @Test
    public void testDeleteAppointmentByAppNum() throws Exception{
        appointmentService.deleteAppointmentByAppNum(123456L);
        verify(appointmentRepository, times(1)).deleteById(123456L);
    }

    @Test
    public void testDeleteAllAppointments() throws Exception{
        appointmentService.deleteAllAppointments();
        verify(appointmentRepository, times(1)).deleteAll();
    }
    @Test
    public void testDuringAvailableTimes_isWeekend() throws Exception{
        LocalDateTime date = LocalDateTime.of(2021,5,9,12,00);
        Appointment appointment = new Appointment(1L,3L,789101L,99443322,date,100.0, AppointmentStatus.AVAILABLE,ConsultantResume.JUNIOR);
        Appointment expectedAppointment = new Appointment(1L,3L,789101L,99443322,date.plusDays(1),100.0, AppointmentStatus.AVAILABLE,ConsultantResume.JUNIOR);

        List<Appointment> consultantApp = new ArrayList<>();
        consultantApp.add(appointment1);

        when(appointmentRepository.findAllByConsultantId(1L)).thenReturn(consultantApp);
        List<Appointment> requestedApp = appointmentService.findAllAppointmentsByConsultant(1L);
        Appointment reqApp = appointmentService.duringAvailableTimes(appointment);

        Assertions.assertEquals(expectedAppointment, reqApp);


    }

    @Test
    public void testDuringAvailableTimes_isSameDate() throws Exception{
        LocalDateTime date = LocalDateTime.of(2021,5,10,10,00);
        Appointment appointment = new Appointment(1L,3L,789101L,99443322,date,100.0, AppointmentStatus.AVAILABLE,ConsultantResume.JUNIOR);
        Appointment expectedAppointment = new Appointment(1L,3L,789101L,99443322,date.plusMinutes(30),100.0, AppointmentStatus.AVAILABLE,ConsultantResume.JUNIOR);

        List<Appointment> consultantApp = new ArrayList<>();
        consultantApp.add(appointment1);

        when(appointmentRepository.findAllByConsultantId(1L)).thenReturn(consultantApp);
        Appointment reqApp = appointmentService.duringAvailableTimes(appointment);

        Assertions.assertEquals(expectedAppointment, reqApp);

    }

}
