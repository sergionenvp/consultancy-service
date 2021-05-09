package com.consultancygroup.appointments.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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



    @FutureOrPresent(message = "Appointment date must be future or present.")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime date;

    @NotNull
    @Min(value = 0, message = "Positive price number")
    private double price;

    @Column(unique = true)
    private int clientContactNum;
    private  ConsultantResume consultantResume;
    private AppointmentStatus status;


    public Appointment(Long consultantId, Long clientId, Long appointmentNum, int clientContactNum,LocalDateTime date,double price,AppointmentStatus status, ConsultantResume consultantResume) {
        this.consultantId=consultantId;
        this.clientId=clientId;
        this.appointmentNum=appointmentNum;
        this.date = date;
        this.price = price;
        this.status = status;
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
