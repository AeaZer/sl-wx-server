package com.study.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ToDate {

    public static String UpDate(String stringDate) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(simpleDateFormat.parse(stringDate));
        c.add(Calendar.DAY_OF_MONTH, 1);   //利用Calendar 实现 Date日期+1天
        Date sDate = c.getTime();
        String format = simpleDateFormat.format(sDate);
        return format;
    }

    public static String getORi(String stringDate) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(simpleDateFormat.parse(stringDate));
        int day=c.get(Calendar.DAY_OF_MONTH);
        c.add(Calendar.DAY_OF_MONTH, -1*day+1);   //利用Calendar 实现 Date日期+1天
        Date sDate = c.getTime();
        String format = simpleDateFormat.format(sDate);
        return format;
    }
}
