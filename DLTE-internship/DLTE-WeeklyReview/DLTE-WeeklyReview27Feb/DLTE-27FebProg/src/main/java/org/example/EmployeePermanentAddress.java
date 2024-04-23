package org.example;

import java.io.Serializable;

public class EmployeePermanentAddress implements Serializable {
    private String permanentHouseName, permanentStreetName, permanentCity, permanentState;
    private Integer pincodePermanent;

    public EmployeePermanentAddress(String permanentHouseName, String permanentStreetName, String permanentCity, String permanentState, Integer pincodePermanent) {

        this.permanentHouseName = permanentHouseName;
        this.permanentStreetName = permanentStreetName;
        this.permanentCity = permanentCity;
        this.permanentState = permanentState;
        this.pincodePermanent = pincodePermanent;
    }

    public EmployeePermanentAddress() {

    }

    public String getPermanentHouseName() {
        return permanentHouseName;
    }

    public void setPermanentHouseName(String permanentHouseName) {
        this.permanentHouseName = permanentHouseName;
    }

    public String getPermanentStreetName() {
        return permanentStreetName;
    }

    public void setPermanentStreetName(String permanentStreetName) {
        this.permanentStreetName = permanentStreetName;
    }

    public String getPermanentCity() {
        return permanentCity;
    }

    public void setPermanentCity(String permanentCity) {
        this.permanentCity = permanentCity;
    }

    public String getPermanentState() {
        return permanentState;
    }

    public void setPermanentState(String permanentState) {
        this.permanentState = permanentState;
    }

    public Integer getPincodePermanent() {
        return pincodePermanent;
    }

    public void setPincodePermanent(Integer pincodePermanent) {
        this.pincodePermanent = pincodePermanent;
    }

    @Override
    public String toString() {
        return "EmployeePermanentAddress{" +
                "permanentHouseName='" + permanentHouseName + '\'' +
                ", permanentStreetName='" + permanentStreetName + '\'' +
                ", permanentCity='" + permanentCity + '\'' +
                ", permanentState='" + permanentState + '\'' +
                ", pincodePermanent=" + pincodePermanent +
                '}';
    }
}
