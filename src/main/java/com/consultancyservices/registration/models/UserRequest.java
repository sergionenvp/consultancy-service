package com.consultancyservices.registration.models;

import javax.validation.constraints.*;

public class UserRequest {

    private String fullName;

    private String locality;
    @Max(value=122,message = "Age cannot be more than 65")
    @Min(value = 1,message = "Age cannot be less than 18")
    private int age;


    public UserRequest(String fullName, String locality, int age) {
        this.fullName = fullName;
        this.locality = locality;
        this.age = age;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
