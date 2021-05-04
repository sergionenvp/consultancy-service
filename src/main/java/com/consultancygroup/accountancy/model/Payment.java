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
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long Id;
    private String cardNumber;
    private String cardHolderName;
    private Date date;
    private String cvc;
    private Long workerId;

    @Min(value = 0, message = "The price must be a positive, it cannot be negative below 0.")
    private double price;

    public Payment(String cardHolderName, String cardNumber, Date date, String cvc, Long workerId, double price){
        this.cardHolderName = cardHolderName;
        this.cardNumber = cardNumber;
        this.date = date;
        this.cvc = cvc;
        this.price = price;
        this.workerId = workerId;
    }

    public void pay(Company company, Worker worker){
        double money = 0;
        double fee = 0;
        if(worker.getResume().equals(ConsultantResume.JUNIOR)) {
            fee = 0.15*getPrice();
            money = getPrice() - fee;
            worker.setBalance(money);
            company.setBalance(fee);

        } else if (worker.getResume().equals(ConsultantResume.EXECUTIVE)) {
            fee = 0.10*getPrice();
            money = getPrice() - fee;
            worker.setBalance(money);
            company.setBalance(fee);

        } else if (worker.getResume().equals(ConsultantResume.SENIOR)) {
            fee = 0.05*getPrice();
            money = getPrice() - fee;
            worker.setBalance(money);
            company.setBalance(fee);
        }
    }
}
