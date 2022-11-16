package com.example.andresjose_comp304sec002_lab5_group13;

public class Airplane {
    private String name;
    private String manufacturer;
    private int cost;
    private int capacity;



    public Airplane(String name, String manufacturer, int cost, int capacity) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.cost = cost;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
