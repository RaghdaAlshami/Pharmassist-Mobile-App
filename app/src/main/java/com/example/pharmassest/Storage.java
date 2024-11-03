package com.example.pharmassest;

public class Storage {
    private  int med_id ;
    private  int com_id ;
    private int shelf;

    public Storage(int med_id, int com_id, int shelf) {
        this.med_id = med_id;
        this.com_id = com_id;
        this.shelf = shelf;
    }

    public void setMed_id(int med_id) {
        this.med_id = med_id;
    }

    public void setCom_id(int com_id) {
        this.com_id = com_id;
    }

    public void setShelf(int shelf) {
        this.shelf = shelf;
    }

    public int getMed_id() {
        return med_id;
    }

    public int getCom_id() {
        return com_id;
    }

    public int getShelf() {
        return shelf;
    }
}
