package com.consultancygroup.accountancy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private String cardNumber;
    private String cardHolderName;
    private Date date;
    private String cvc;
    private Long workerId;
    private ConsultantResume resume;
    private double workerMoney;
    private double commissionCompany;

    @Min(value = 0, message = "The price must be a positive, it cannot be negative below 0.")
    private double price;

    public Payment(Long id, String cardHolderName, String cardNumber, Date date, String cvc, Long workerId, ConsultantResume resume, double price){
        this.id = id;
        this.cardHolderName = cardHolderName;
        this.cardNumber = cardNumber;
        this.date = date;
        this.cvc = cvc;
        this.price = price;
        this.workerId = workerId;
        this.resume = resume;
    }
}
