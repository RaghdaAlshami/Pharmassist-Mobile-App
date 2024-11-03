package com.example.pharmassest;

public class User {


    private  int id ;
    private  String name ;
    private String password;
    private  String roll ;

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

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }
    public User(int id, String name, String password, String roll) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.roll = roll;
    }
    public User(String name, String password, String roll) {
        this.name = name;
        this.password = password;
        this.roll = roll;
    }

}

