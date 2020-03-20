package com.example.benzinpreise;

public class StationPreis {

    private String id;
    private String name;
    private String brand;
    private String street;
    private String place;
    private double lat;
    private double lng;
    private double dist;
    private String price;
    private boolean isOpen;
    private String houseNumber;
    private int postCode;
    private boolean expand;

    public StationPreis() {
    }


    public StationPreis(String name, String brand, String place, double dist, String price, boolean isOpen, boolean expand) {
        this.name = name;
        this.brand = brand;
        this.place = place;
        this.dist = dist;
        this.price = price;
        this.isOpen = isOpen;
        this.expand = false;
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public String getStreet() {
        return street;
    }

    public String getPlace() {
        return place;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public double getDist() {
        return dist;
    }

    public String getPrice() {
        return price;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public int getPostCode() {
        return postCode;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public void setDist(double dist) {
        this.dist = dist;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public void setPostCode(int postCode) {
        this.postCode = postCode;
    }

    public boolean isExpand() {
        return expand;
    }

    public void setExpand(boolean expand) {
        this.expand = expand;
    }
}

