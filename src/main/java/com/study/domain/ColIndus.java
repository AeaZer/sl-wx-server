package com.study.domain;

import java.io.Serializable;
import java.util.List;

public class ColIndus implements Serializable {
    private String industries_code;
    private String industries;
    private float daySale_1;
    private float monthSale_1;
    private List<ColSec> colSecs;

    public String getIndustries_code() {
        return industries_code;
    }

    public void setIndustries_code(String industries_code) {
        this.industries_code = industries_code;
    }

    public String getIndustries() {
        return industries;
    }

    public void setIndustries(String industries) {
        this.industries = industries;
    }

    public float getDaySale_1() {
        return daySale_1;
    }

    public void setDaySale_1(float daySale_1) {
        this.daySale_1 = daySale_1;
    }

    public float getMonthSale_1() {
        return monthSale_1;
    }

    public void setMonthSale_1(float monthSale_1) {
        this.monthSale_1 = monthSale_1;
    }

    public List<ColSec> getColSecs() {
        return colSecs;
    }

    public void setColSecs(List<ColSec> colSecs) {
        this.colSecs = colSecs;
    }
}
