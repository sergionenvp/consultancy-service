package com.consultancygroup.accountancy.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import java.util.Date;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String cardNumber;
    private String cardHolderName;
    private Date date;
    private String cvc;

    @Min(value = 0, message = "The price must be a positive, it cannot be negative below 0.")
    private double price;

    public Payment(){
        this(null, null, null, null, 0);
    }

    public Payment(String cardHolderName, String cardNumber, Date date, String cvc, double price){
        this.cardHolderName = cardHolderName;
        this.cardNumber = cardNumber;
        this.date = date;
        this.cvc = cvc;
        this.price = price;
    }
    public Payment(Long id, String cardHolderName, String cardNumber, Date date, String cvc, double price){
        this.Id = id;
        this.cardHolderName = cardHolderName;
        this.cardNumber = cardNumber;
        this.date = date;
        this.cvc = cvc;
        this.price = price;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public void setId(Long id){
        this.Id = id;
    }

    public void setCardNumber(String cardNumber){
        this.cardNumber = cardNumber;
    }

    public void setCardHolderName(String cardHolderName){
        this.cardHolderName = cardHolderName;
    }

    public void setDate(Date date){
        this.date = date;
    }

    public void setCvc(String cvc){
        this.cvc = cvc;
    }

    public Long getId(){
        return Id;
    }

    public String getCardNumber(){
        return cardNumber;
    }

    public String getCardHolderName(){
        return cardHolderName;
    }

    public Date getDate(){
        return date;
    }

    public String getCvc(){
        return cvc;
    }

    public double getPrice(){
        return price;
    }
}
