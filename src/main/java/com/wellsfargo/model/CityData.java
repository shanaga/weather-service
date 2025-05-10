package com.wellsfargo.model;

public class CityData {

    public CityData() {

    }

    public CityData(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
