package com.study.domain;

import java.io.Serializable;

public class ColRoom implements Serializable {
    private String saleRoom_code;
    private String saleRoom;
    private float daySale_3;
    private float monthSale_3;

    public String getSaleRoom_code() {
        return saleRoom_code;
    }

    public void setSaleRoom_code(String saleRoom_code) {
        this.saleRoom_code = saleRoom_code;
    }

    public String getSaleRoom() {
        return saleRoom;
    }

    public void setSaleRoom(String saleRoom) {
        this.saleRoom = saleRoom;
    }

    public float getDaySale_3() {
        return daySale_3;
    }

    public void setDaySale_3(float daySale_3) {
        this.daySale_3 = daySale_3;
    }

    public float getMonthSale_3() {
        return monthSale_3;
    }

    public void setMonthSale_3(float monthSale_3) {
        this.monthSale_3 = monthSale_3;
    }
}
