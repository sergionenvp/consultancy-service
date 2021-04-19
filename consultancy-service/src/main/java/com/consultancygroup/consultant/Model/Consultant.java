package com.consultancygroup.consultant.Model;
import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.bridge.IMessage;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.io.Serializable;


//Generate Boiler plate code
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Consultant implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long consultantId;

    @javax.validation.constraints.NotNull(message = "Bad request: fullName cannot be null ")
    @NotBlank(message = "Bad request: fullName cannot be blank ")
    private String fullName;
    @Min(value = 18, message = "Bad request: age cannot less than 18")
    @Max(value = 70, message = "Bad request: age more than 70")
    @javax.validation.constraints.NotNull(message = "Bad request: age null")
    private int age;
    private  ConsultantResume consultantResume;

    public Consultant(String fullName, int age, ConsultantResume consultantResume) {
        this.fullName=fullName;
        this.age=age;
        this.consultantResume=consultantResume;
    }

}

