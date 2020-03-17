package com.example.benzinpreise;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class Stations {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("brand")
    @Expose
    private String brand;
    @SerializedName("street")
    @Expose
    private String street;
    @SerializedName("place")
    @Expose
    private String place;
    @SerializedName("lat")
    @Expose
    private double lat;
    @SerializedName("lng")
    @Expose
    private double lng;
    @SerializedName("dist")
    @Expose
    private double dist;
    @SerializedName("diesel")
    @Expose
    private double diesel;
    @SerializedName("e5")
    @Expose
    private double e5;
    @SerializedName("e10")
    @Expose
    private double e10;
    @SerializedName("isOpen")
    @Expose
    private boolean isOpen;
    @SerializedName("houseNumber")
    @Expose
    private int houseNumber;
    @SerializedName("postCode")
    @Expose
    private int postCode;

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

    public double getDiesel() {
        return diesel;
    }

    public double getE5() {
        return e5;
    }

    public double getE10() {
        return e10;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public int getPostCode() {
        return postCode;
    }
}
