package com.consultancygroup.registration.models;

public class RegistrationRequest {
    private String fullName;
    private int age;
    private UserType userType;
    public RegistrationRequest(String fullName, int age, UserType userType) {
        this.fullName=fullName;
        this.age=age;
        this.userType=userType;
    }
    public  String getFullName() {return fullName;}
    public void setFullName(String fullName){this.fullName=fullName;}
    public int getAge() {return  age;}
    public void setAge(int age){this.age=age;}
    public UserType getUserType(){return  userType;}
    public void setUserType(UserType userType){this.userType=userType;}
}
