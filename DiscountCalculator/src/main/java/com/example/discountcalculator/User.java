package com.example.discountcalculator;

import java.sql.Date;

public class User {
    private String name;
    private String dob;
    private String address;
    private String imgProfile;
    public User(String name, String dob, String address, String imgProfile){
        this.name=name;
        this.dob=dob;
        this.address=address;
        this.imgProfile=imgProfile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImgProfile() {
        return imgProfile;
    }

    public void setImgProfile(String imgProfile) {
        this.imgProfile = imgProfile;
    }
}
