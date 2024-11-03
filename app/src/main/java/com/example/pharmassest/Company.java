package com.example.pharmassest;

public class Company {


    private  int id ;
    private  String name ;
    private String closet;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCloset() {
        return closet;
    }
    public void setCloset(String password) {
        this.closet = password;
    }

    public Company(int id, String name, String closet) {
        this.id = id;
        this.name = name;
        this.closet = closet;

    }
    public Company(String name, String closet) {
        this.name = name;
        this.closet = closet;

    }

}

