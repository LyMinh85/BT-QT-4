package com.example.bt_qt_4;

public class Chanels {
    private String type;
    private String id;

    public Chanels(){

    }

    public Chanels(String type, String id){
        this.type = type;
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}