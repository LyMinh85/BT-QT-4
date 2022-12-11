package com.example.bt_qt_4;

import java.util.ArrayList;

public class Office {
    private String name;
    private ArrayList<Integer> officialIndices;

    public Office(String name, ArrayList<Integer> officialIndices) {
        this.name = name;
        this.officialIndices = officialIndices;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Integer> getOfficialIndices() {
        return officialIndices;
    }

    public void setOfficialIndices(ArrayList<Integer> officialIndices) {
        this.officialIndices = officialIndices;
    }

    @Override
    public String toString() {
        return "Office{" +
                "name='" + name + '\'' +
                ", officialIndices=" + officialIndices +
                '}';
    }
}
