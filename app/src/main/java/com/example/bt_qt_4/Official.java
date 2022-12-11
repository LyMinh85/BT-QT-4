package com.example.bt_qt_4;

import java.io.Serializable;
import java.util.ArrayList;

public class Official implements Serializable {
    private String name;
    private String position;
    private String party;
    private Address address;
    private String phone;
    private String email;
    private String url;
    private String photoUrl;
    private ArrayList<Channel> channels;

    public Official(String position, String name, String party){
        this.position = position;
        this.name = name;
        this.party = party;
    }

    public Official(){

    }

    public Official(String name, String position, String party, Address address, String phone, String email, String url, String photoUrl, ArrayList<Channel> channels) {
        this.name = name;
        this.position = position;
        this.party = party;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.url = url;
        this.photoUrl = photoUrl;
        this.channels = channels;
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

    public String getEmail() {
        return email;
    }

    public String getParty() {
        return "(" + party + ")";
    }

    public String getPhone() {
        return phone;
    }

    public String getPosition() {
        return position;
    }

    public String getUrl() {
        return url;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public ArrayList<Channel> getChannels() {
        return channels;
    }

    public String getChannel(String type) {
        for (Channel channel: channels) {
            if (channel.getType().equalsIgnoreCase(type)) {
                return channel.getId();
            }
        }
        return "";
    }

    public void setChannels(ArrayList<Channel> channels) {
        this.channels = channels;
    }

    @Override
    public String toString() {
        return "Official{" +
                "name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", party='" + party + '\'' +
                ", address=" + address +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", url='" + url + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                ", channels=" + channels +
                '}';
    }
}
