package Models;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import java.time.LocalDateTime;



public class Appointment {
    private Long consultantId;
    private Long clientId;
    private Long appointmentNum;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime date;
    private double price;
    private int clientContactNum;
    private ConsultantResume consultantResume;
    private AppointmentStatus status;

    public Long getClientId() {
        return clientId;
    }

    public Long getAppointmentNum() {
        return appointmentNum;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public double getPrice() {
        return price;
    }

    public int getClientContactNum() {
        return clientContactNum;
    }

    public ConsultantResume getConsultantResume() {
        return consultantResume;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setConsultantId(Long consultantId) {
        this.consultantId = consultantId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public void setAppointmentNum(Long appointmentNum) {
        this.appointmentNum = appointmentNum;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setClientContactNum(int clientContactNum) {
        this.clientContactNum = clientContactNum;
    }

    public void setConsultantResume(ConsultantResume consultantResume) {
        this.consultantResume = consultantResume;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    public Long getConsultantId() {
        return consultantId;
    }
}