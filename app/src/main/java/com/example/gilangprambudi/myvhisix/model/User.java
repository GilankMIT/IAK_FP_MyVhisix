package com.example.gilangprambudi.myvhisix.model;

/**
 * Created by Gilang Prambudi on 16/02/2018.
 */

public class User {

    private String name;
    private String address;
    private String email;

    public User(String name, String address, String email) {
        this.name = name;
        this.address = address;
        this.email = email;
    }

    public User() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return this.email;
    }

}
