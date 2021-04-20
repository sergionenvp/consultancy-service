package com.consultancygroup.consultant.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import java.io.Serializable;


//Generate Boiler plate code
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class    Consultant implements Serializable {

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

    //consultantPhoneNumber unique
    @Column(unique = true)
    private int phoneNumber;
    private  ConsultantResume consultantResume;

    public Consultant(String fullName, int age, int phoneNumber, ConsultantResume consultantResume) {
        this.fullName=fullName;
        this.age=age;
        this.consultantResume=consultantResume;
        this.phoneNumber=phoneNumber;
    }

}

