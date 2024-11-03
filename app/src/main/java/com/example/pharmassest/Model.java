package com.example.pharmassest;

public class Model {
    String med,com,desc,closet,shelf;


    public Model(String med, String com, String desc, String closet, String shelf) {
        this.med = med;
        this.com = com;
        this.desc = desc;
        this.closet = closet;
        this.shelf = shelf;
    }

    public void setMed(String med) {
        this.med = med;
    }

    public void setCom(String com) {
        this.com = com;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setCloset(String closet) {
        this.closet = closet;
    }

    public void setShelf(String shelf) {
        this.shelf = shelf;
    }

    public String getMed() {
        return med;
    }

    public String getCom() {
        return com;
    }

    public String getDesc() {
        return desc;
    }

    public String getCloset() {
        return closet;
    }

    public String getShelf() {
        return shelf;
    }
}
