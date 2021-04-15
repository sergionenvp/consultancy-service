package com.consultancygroup.consultancyservice.consultants.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Consultant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long consultantId;
    private String fullName;
    private int age;
    Consultant () {}



}
