package com.consultancygroup.accountancy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    private double balance;

    public void setBalance(double balance){
        this.balance += balance;
    }

}
