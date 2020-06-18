package com.example.tempapp;

public class ListItem {

    private String state;
    private String district;
    private String active;
    private String cases;
    private String deaths;
    private String recover;

    public ListItem(String state, String district,String active, String cases, String deaths, String recover) {
        this.state = state;
        this.district = district;
        this.active = active;
        this.cases = cases;
        this.deaths = deaths;
        this.recover = recover;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCases() {
        return cases;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getRecover() {
        return recover;
    }

    public void setRecover(String recover) {
        this.recover = recover;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }
}
