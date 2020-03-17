package com.example.benzinpreise;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Tankstellen {

    private boolean ok;
    private String license;
    private String data;
    private String status;
    @SerializedName("stations")
    public List <Stations> stations =null;

    public class Stations{
        @SerializedName("id")
        public String id;
        @SerializedName("name")
        public String name;
        @SerializedName("brand")
        public String brand;
        @SerializedName("street")
        public String street;
        @SerializedName("place")
        public String place;
        @SerializedName("lat")
        public double lat;
        @SerializedName("lng")
        public double lng;
        @SerializedName("dist")
        public double dist;
        @SerializedName("diesel")
        public double diesel;
        @SerializedName("e5")
        public double e5;
        @SerializedName("e10")
        public double e10;
        @SerializedName("isOpen")
        public boolean isOpen;
        @SerializedName("houseNumber")
        public String houseNumber;
        @SerializedName("postCode")
        public int postCode;


    }


    public boolean isOk() {
        return ok;
    }

    public String getLicense() {
        return license;
    }

    public String getData() {
        return data;
    }

    public String getStatus() {
        return status;
    }

}
