package com.study.domain;

import java.io.Serializable;
import java.util.List;

public class ColSec implements Serializable {
    private String secondLevel_code;
    private String secondLevel;
    private float daySale_2;
    private float monthSale_2;
    private List<ColRoom> colRooms;

    public String getSecondLevel_code() {
        return secondLevel_code;
    }

    public void setSecondLevel_code(String secondLevel_code) {
        this.secondLevel_code = secondLevel_code;
    }

    public String getSecondLevel() {
        return secondLevel;
    }

    public void setSecondLevel(String secondLevel) {
        this.secondLevel = secondLevel;
    }

    public float getDaySale_2() {
        return daySale_2;
    }

    public float getMonthSale_2() {
        return monthSale_2;
    }

    public void setDaySale_2(float daySale_2) {
        this.daySale_2 = daySale_2;
    }


    public void setMonthSale_2(float monthSale_2) {
        this.monthSale_2 = monthSale_2;
    }

    public List<ColRoom> getColRooms() {
        return colRooms;
    }

    public void setColRooms(List<ColRoom> colRooms) {
        this.colRooms = colRooms;
    }
}
