package com.example.bt_qt_4;

public class Officials {
    private String name;
    private String position;
    private String party;
    private Address address;
    private String phones;
    private String emails;
    private String urls;

    public Officials (String position, String name, String party){
        this.position = position;
        this.name = name;
        this.party = party;
    }

    public Officials(){

    }

    public  Officials(String name, String position, String party, Address address, String phones, String emails, String urls){
        this.name = name;
        this.position = position;
        this.party = party;
        this.address = address;
        this.phones = phones;
        this.emails = emails;
        this.urls = urls;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    public String getEmails() {
        return emails;
    }

    public String getParty() {
        return party;
    }

    public String getPhones() {
        return phones;
    }

    public String getPosition() {
        return position;
    }

    public String getUrls() {
        return urls;
    }

    public void setEmails(String emails) {
        this.emails = emails;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public void setPhones(String phones) {
        this.phones = phones;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setUrls(String urls) {
        this.urls = urls;
    }
}
