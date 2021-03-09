package com.study.API;


import org.springframework.stereotype.Repository;

@Repository
public class WxAPI {
    private String code;
    private String msg;
    private Object data;
    private float dayTotal;
    private float monthTotal;

    public float getDayTotal() {
        return dayTotal;
    }

    public void setDayTotal(float dayTotal) {
        this.dayTotal = dayTotal;
    }

    public float getMonthTotal() {
        return monthTotal;
    }

    public void setMonthTotal(float monthTotal) {
        this.monthTotal = monthTotal;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "WxAPI{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
