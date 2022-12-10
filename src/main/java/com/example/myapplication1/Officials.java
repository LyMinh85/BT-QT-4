package com.example.myapplication1;

public class Officials {
    private String name;
    private String posision;
    private String party;
    private Address address;
    private String phones;
    private String emails;
    private String urls;
    public Officials(){

    }
    public  Officials(String name, String posision, String party, Address address, String phones, String emails, String urls){
        this.name = name;
        this.posision = posision;
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

    public String getPosision() {
        return posision;
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

    public void setPosision(String posision) {
        this.posision = posision;
    }

    public void setUrls(String urls) {
        this.urls = urls;
    }
}
