package com.consultancygroup.appointments.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor


public class Appointment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long consultantId;
    private Long clientId;
    private Long appointmentNum;

   /* @NotNull
    @FutureOrPresent(message = "Appointment date must be future or present.")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date1;*/

    @Column(unique = true)
    private int clientContactNum;
    private  ConsultantResume consultantResume;
    private String date;

    public Appointment(Long consultantId, Long clientId, Long appointmentNum, int clientContactNum,String date, ConsultantResume consultantResume) {
        this.consultantId=consultantId;
        this.clientId=clientId;
        this.appointmentNum=appointmentNum;
        this.date = date;
        this.clientContactNum = clientContactNum;
        this.consultantResume=consultantResume;
    }

 /*   public void setDate(LocalDate endDate) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }*/
}
//https://github.com/getstarted-spring/restcontroller-datetime-format-json-response/blob/master/src/main/resources/META-INF/spring.factories