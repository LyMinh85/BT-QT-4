package com.example.bt_qt_4;

public class Officials {
    private String position;
    private String name;
    private String party;

    public Officials (String position, String name, String party){
        this.position = position;
        this.name = name;
        this.party = party;
    }

    public Officials(){

    }

    public String getPosition() {
        return position;
    }

    public void setPositon(String position){
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getParty() {
        return " (" + party + ")";
    }

    public void setParty(String party){
        this.party = party;
    }
}
