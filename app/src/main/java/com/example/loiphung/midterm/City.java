package com.example.loiphung.midterm;

/**
 * Created by LoiPhung on 3/12/18.
 */

public class City {
    String state, city , country, zmw = "";

    public City(String state, String city) {
        this.state = state;
        this.city = city;
    }

    public City() {
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String toString(){
        return (city + " , "+ "\n" + country );
    }

    public String getZmw() {
        return zmw;
    }

    public void setZmw(String zmw) {
        this.zmw = zmw;
    }
}
