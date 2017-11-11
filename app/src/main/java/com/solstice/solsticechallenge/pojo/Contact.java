package com.solstice.solsticechallenge.pojo;

public class Contact {
    private String emailAddress;
    private String birthdate;
    private Address address;
    private String smallImageURL;
    private Phone phone;
    private String companyName;
    private String largeImageURL;
    private String name;
    private String id;
    private boolean isFavorite;

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    public void setSmallImageURL(String smallImageURL) {
        this.smallImageURL = smallImageURL;
    }

    public String getSmallImageURL() {
        return smallImageURL;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setLargeImageURL(String largeImageURL) {
        this.largeImageURL = largeImageURL;
    }

    public String getLargeImageURL() {
        return largeImageURL;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setIsFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

    public boolean isIsFavorite() {
        return isFavorite;
    }

    @Override
    public String toString() {
        return
                "ServerResponse{" +
                        "emailAddress = '" + emailAddress + '\'' +
                        ",birthdate = '" + birthdate + '\'' +
                        ",address = '" + address + '\'' +
                        ",smallImageURL = '" + smallImageURL + '\'' +
                        ",phone = '" + phone + '\'' +
                        ",companyName = '" + companyName + '\'' +
                        ",largeImageURL = '" + largeImageURL + '\'' +
                        ",name = '" + name + '\'' +
                        ",id = '" + id + '\'' +
                        ",isFavorite = '" + isFavorite + '\'' +
                        "}";
    }
}
