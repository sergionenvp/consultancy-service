package com.consultancygroup.appointments.appointmentController;

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
import org.json.JSONObject;
import org.json.JSONString;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
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

public class AppointmentControllerTest {
    private static final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private TestRestTemplate testRestTemplate;

    @MockBean
    private AppointmentService appointmentMockService;

    Appointment appointment1;
    Appointment appointment2;

    List<Appointment> appointmentsList = new ArrayList<>();

    @BeforeEach
    public void setup() {
         appointment1 = new Appointment(1L,2L,123456L,79123456,LocalDateTime.of(2021,6,20,10,00),100.0, AppointmentStatus.AVAILABLE,ConsultantResume.JUNIOR);
         appointment2 = new Appointment(11L,21L,123457L,79123457,LocalDateTime.of(2021,6,21,10,00),200.0, AppointmentStatus.AVAILABLE,ConsultantResume.EXECUTIVE);

         appointmentsList.add(appointment1);
         appointmentsList.add(appointment2);
    }

   @Test
    public void testCreateAppointment() throws Exception{


        Appointment appointment = new Appointment(1L,2L,123456L,79123456,LocalDateTime.of(2021,6,20,10,00),100.0, AppointmentStatus.AVAILABLE,ConsultantResume.JUNIOR);

        String expectedResponseBody = mapper.writeValueAsString(appointment);
        String endpoint = "/appointments";

        Appointment app = new Appointment(1L,2L,123456L,79123456,LocalDateTime.of(2021,6,20,10,00),100.0, AppointmentStatus.AVAILABLE,ConsultantResume.JUNIOR);
        when(appointmentMockService.saveAppointment(any(Appointment.class))).thenReturn(app);

        ResponseEntity<String> responseEntity = testRestTemplate.postForEntity(endpoint, appointment, String.class);

        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        JSONAssert.assertEquals(expectedResponseBody, responseEntity.getBody(), true);
    }
    @Test
    public void testGetAppointmentByAppointmentNum() throws Exception{
        when(appointmentMockService.findAppByAppNum(123456L)).thenReturn(appointment1);
        String result = mapper.writeValueAsString(appointment1);
        String endpoint = "/appointments/id/123456";

        ResponseEntity<String> response = testRestTemplate.getForEntity(endpoint, String.class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        JSONAssert.assertEquals(result, response.getBody(), true);

    }

    @Test
    public void testGetAppointmentBy_InvalidAppointmentNum() throws Exception{
        String endpoint = "/appointments/id/2203";
        ResponseEntity<String> response = testRestTemplate.getForEntity(endpoint, String.class);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testGetAppointmentByConsultantAndDate() throws Exception{

        LocalDateTime date = LocalDateTime.of(2021,6,20,10,30);
        Appointment appointment = new Appointment(1L,3L,789101L,99443322,LocalDateTime.of(2021,6,20,10,30),100.0, AppointmentStatus.AVAILABLE,ConsultantResume.JUNIOR);

        List<Appointment> consultantAppList = new ArrayList<>();
        consultantAppList.add(appointment);
        consultantAppList.add(appointment1);

        String result = mapper.writeValueAsString(consultantAppList);
        when(appointmentMockService.findAppsByConsultantAndDate(1L,date.toLocalDate())).thenReturn(consultantAppList);

        String endpoint = "/appointments/date/1/" + date.toLocalDate();
        ResponseEntity<String> response = testRestTemplate.getForEntity(endpoint, String.class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(result, response.getBody());

    }

    @Test
    public void testGetAllAppointmentsByConsultantId() throws Exception{
        Appointment appointment = new Appointment(1L,3L,789101L,99443322,LocalDateTime.of(2021,6,10,10,30),100.0, AppointmentStatus.AVAILABLE,ConsultantResume.JUNIOR);

        List<Appointment> consultantAppList = new LinkedList<Appointment>();
        consultantAppList.add(appointment);
        consultantAppList.add(appointment1);

        String result = mapper.writeValueAsString(consultantAppList);

        when(appointmentMockService.findAllAppointmentsByConsultant(1L)).thenReturn(consultantAppList);

        String endpoint = "/appointments/consultant/1";
        ResponseEntity<String> response = testRestTemplate.getForEntity(endpoint, String.class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(result, response.getBody());
    }

    @Test
    public void testUpdateAppointment() throws Exception{
        Appointment appointment = new Appointment(1L,3L,789101L,99443322,LocalDateTime.of(2021,6,20,10,30),100.0, AppointmentStatus.AVAILABLE,ConsultantResume.JUNIOR);
        when(appointmentMockService.findAppByAppNum(789101L)).thenReturn(appointment);

        Appointment updateAppointment = new Appointment(1L,3L,789101L,99443322,LocalDateTime.of(2021,6,20,13,30),100.0, AppointmentStatus.AVAILABLE,ConsultantResume.JUNIOR);
        Appointment expectedAppointment = new Appointment(1L,3L,789101L,99443322,LocalDateTime.of(2021,6,20,13,30),100.0, AppointmentStatus.AVAILABLE,ConsultantResume.JUNIOR);
        when(appointmentMockService.saveAppointment(any(Appointment.class))).thenReturn(expectedAppointment);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(mapper.writeValueAsString(updateAppointment), headers);

        String endpoint = "/appointments/update/789101";

        ResponseEntity<String> response = testRestTemplate.exchange(endpoint, HttpMethod.PUT, entity, String.class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        JSONAssert.assertEquals(mapper.writeValueAsString(updateAppointment), response.getBody(), true);

        verify(appointmentMockService, times(1)).findAppByAppNum(789101L);
        verify(appointmentMockService, times(1)).saveAppointment(any(Appointment.class));
    }

    @Test
    public void testDeleteAllAppointments() throws Exception{
        doNothing().when(appointmentMockService).deleteAllAppointments();
        HttpEntity<String> httpEntity = new HttpEntity<>(null, new HttpHeaders());

        String endpoint = "/appointments/delete/all";

        ResponseEntity<String> response = testRestTemplate.exchange(endpoint, HttpMethod.DELETE, httpEntity, String.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(appointmentMockService, times(1)).deleteAllAppointments();
    }

    @Test
    public void testDeleteByAppNum() throws Exception{
        doNothing().when(appointmentMockService).deleteAppointmentByAppNum(123456L);
        HttpEntity<String> entity = new HttpEntity<>(null, new HttpHeaders());

        String endpoint = "/appointments/id/123456";

        ResponseEntity<String> response = testRestTemplate.exchange(endpoint, HttpMethod.DELETE, entity, String.class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(appointmentMockService, times(1)).deleteAppointmentByAppNum(123456L);
    }

    @Test
    public void testExport() throws JsonProcessingException {
        List<Appointment> expectedAppointments = new LinkedList<Appointment>();
        expectedAppointments.add(appointment1);
        expectedAppointments.add(appointment2);

        String endpoint = "/appointment/export";
        ResponseEntity<String> responseEntity = testRestTemplate.getForEntity(endpoint, String.class);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }











}
