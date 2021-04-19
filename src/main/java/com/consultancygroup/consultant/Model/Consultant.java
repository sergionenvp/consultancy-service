package com.consultancygroup.consultant.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.bridge.IMessage;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Null;


//Generate Boiler plate code
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Consultant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long consultantId;

    @javax.validation.constraints.NotNull(message = "Bad request: fullName cannot be null ")
    private String fullName;
    @Min(value = 18, message = "Bad request: age cannot less than 18")
    @Max(value = 70, message = "Bad request: age more than 70")
    @javax.validation.constraints.NotNull(message = "Bad request: age null")
    private int age;
    private  ConsultantResume consultantResume;

}

