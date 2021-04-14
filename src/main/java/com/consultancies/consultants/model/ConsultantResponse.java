package com.consultancies.consultants.model;

public class ConsultantResponse {
    private String fullName;
    private int age;
    //important to have a unique id
    private int id;
    public ConsultantResponse(int id, String fullName, int age){
        this.id=id;
        this.fullName=fullName;
        this.age=age;

    }
    public String getFullName(){return fullName;}
    public void setFullName(String  fullName){this.fullName=fullName;}
    public int getAge(){return  age;}
    public void setAge(int age){this.age=age;}
    public int getId(){return id;}
}
