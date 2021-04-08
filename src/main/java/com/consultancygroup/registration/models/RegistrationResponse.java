package com.consultancygroup.registration.models;

public class RegistrationResponse {
    private String fullName;
    private int age;
    private UserType userType;
    public RegistrationResponse(String fullName,int age, UserType userType) {
        this.userType=userType;
        this.age=age;
        this.fullName=fullName;
    }
    public String getFullName(){ return fullName;}
    public void setFullName(String fullName){ this.fullName=fullName; }
    public int getAge(){ return age; }
    public void setAge(int age){ this.age=age; }
    public UserType getUserType() {return userType;}
    public void setUserType(UserType userType){this.userType=userType;}
}
