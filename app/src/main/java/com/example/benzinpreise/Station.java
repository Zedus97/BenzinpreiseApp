package com.example.benzinpreise;

public class Station {

        private String id;
        private String name;
        private String brand;
        private String street;
        private String place;
        private double lat;
        private double lng;
        private double dist;
        private double diesel;
        private double e5;
        private double e10;
        private boolean isOpen;
        private String houseNumber;
        private int postCode;

        public Station(){}

        public Station (String name, String brand, String place, double dist, double diesel, double e10, double e5, boolean isOpen){
            this.name = name;
            this.brand = brand;
            this.place = place;
            this.dist = dist;
            this.diesel = diesel;
            this.e10 = e10;
            this.e5 = e5;
            this.isOpen = isOpen;
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

        public void setDiesel(double diesel) {
            this.diesel = diesel;
        }

        public void setE5(double e5) {
            this.e5 = e5;
        }

        public void setE10(double e10) {
            this.e10 = e10;
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
    }

