package com.example.benzinpreise.apiRequest;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TankstellenPreis {

    private boolean ok;
    private String license;
    private String data;
    private String status;
    @SerializedName("stations")
    public List<Stations> stationsPreis;

    public class Stations {
        @SerializedName("id")
        private String id;
        @SerializedName("name")
        private String name;
        @SerializedName("brand")
        private String brand;
        @SerializedName("street")
        private String street;
        @SerializedName("place")
        private String place;
        @SerializedName("lat")
        private double lat;
        @SerializedName("lng")
        private double lng;
        @SerializedName("dist")
        private double dist;
        @SerializedName("price")
        private String price;
        @SerializedName("isOpen")
        private boolean isOpen;
        @SerializedName("houseNumber")
        private String houseNumber;
        @SerializedName("postCode")
        private int postCode;

        public String getPrice() {
            return price;
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
    }
}
