package com.solstice.solsticechallenge.pojo;

public class Address {
    private String country;
    private String zipCode;
    private String city;
    private String street;
    private String state;

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreet() {
        return street;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    @Override
    public String toString() {
        return
                "Address{" +
                        "country = '" + country + '\'' +
                        ",zipCode = '" + zipCode + '\'' +
                        ",city = '" + city + '\'' +
                        ",street = '" + street + '\'' +
                        ",state = '" + state + '\'' +
                        "}";
    }
}
