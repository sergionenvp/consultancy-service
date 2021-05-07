package com.consultancygroup.appointments.appointmentController;

import com.consultancygroup.appointments.AppointmentsService.AppointmentService;
import com.consultancygroup.appointments.model.Appointment;
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
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Date;
import java.text.*;
import java.time.LocalDate;
import java.text.ParseException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")

public class AppointmentControllerTest {
    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private TestRestTemplate testRestTemplate;

    @MockBean
    private AppointmentService appointmentMockService;

   @Test
    public void testCreateAppointment() throws Exception{

      //
        Appointment appointment = new Appointment(1L,2L,123456L,79123456,"10/5/2021 10:00:00",ConsultantResume.JUNIOR);

        String expectedResponseBody = om.writeValueAsString(appointment);
        String endpoint = "/appointments";

        Appointment app = new Appointment(1L,2L,123456L,79123456,"10/5/2021 10:00:00",ConsultantResume.JUNIOR);
        when(appointmentMockService.saveAppointment(any(Appointment.class))).thenReturn(app);

        ResponseEntity<String> responseEntity = testRestTemplate.postForEntity(endpoint, appointment, String.class);

        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        JSONAssert.assertEquals(expectedResponseBody, responseEntity.getBody(), true);
    }

    @Test
    public void testGetAppointmentByAppointmentNum() throws Exception{

    }

    @Test
    public void testGetAppointmentByConsultantAndDate() throws Exception{

    }

    @Test
    public void testGetAllAppointmentsByConsultantId() throws Exception{

    }

    @Test
    public void testUpdateAppointment() throws Exception{

    }

    @Test
    public void testDeleteAllAppointments() throws Exception{

    }

    @Test
    public void testDeleteByAppNum() throws Exception{

    }












}
