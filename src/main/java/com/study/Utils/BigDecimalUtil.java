package com.study.Utils;

import java.math.BigDecimal;

public class BigDecimalUtil {

    public static float round(float F){
        BigDecimal bigDecimal = new BigDecimal(F);
        return  bigDecimal.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
    }

    public static float add(float a, float b) {
        BigDecimal b1 = new BigDecimal(a+"");
        BigDecimal b2 = new BigDecimal(b + "");
        float F = b1.add(b2).floatValue();
        return F;
    }
}
