package com.solstice.solsticechallenge.pojo;

public class Phone {
    private String work;
    private String mobile;
    private String home;

    public void setWork(String work) {
        this.work = work;
    }

    public String getWork() {
        return work;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return mobile;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getHome() {
        return home;
    }

    @Override
    public String toString() {
        return
                "Phone{" +
                        "work = '" + work + '\'' +
                        ",mobile = '" + mobile + '\'' +
                        ",home = '" + home + '\'' +
                        "}";
    }
}
