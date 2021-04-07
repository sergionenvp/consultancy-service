package com.consultancygroup.registration.models;

public class User {

    private Long Id;
    private String fullName;
    private String locality;
    private int age;
    private Boolean isActive;

    public User() {
        this(null, null, null, 0);
    }

    public User(String fullName, String locality, int age) {
        this(null, fullName, locality, age);
    }

    public User(Long id, String fullName, String locality, int age) {
        this.setId(id);
        this.fullName = fullName;
        this.locality = locality;
        this.age = age;

    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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

    public Boolean getIsActive() {return isActive;}

    public void setIsActive(Boolean isActive){this.isActive=isActive;}
}