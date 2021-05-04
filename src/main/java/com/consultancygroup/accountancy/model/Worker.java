package com.consultancygroup.accountancy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Worker {

    private Long id;
    private double balance;
    private ConsultantResume resume;

    public void setBalance(double balance){
        this.balance += balance;
    }
}
