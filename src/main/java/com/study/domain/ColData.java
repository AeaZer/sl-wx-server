package com.study.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ColData implements Serializable {

//    private List<ColRoom> data_arr;
    private float dayTotal;
    private Date dateArr;

/*    public List<ColRoom> getData_arr() {
        return data_arr;
    }

    public void setData_arr(List<ColRoom> data_arr) {
        this.data_arr = data_arr;
    }*/

    public float getDayTotal() {
        return dayTotal;
    }

    public void setDayTotal(float dayTotal) {
        this.dayTotal = dayTotal;
    }

    public Date getDateArr() {
        return dateArr;
    }

    public void setDateArr(Date dateArr) {
        this.dateArr = dateArr;
    }
}
