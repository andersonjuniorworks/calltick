package com.andersonjunior.calltick.component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CaptureIntervalDate {

    public String getFirstDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        date = calendar.getTime();
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        date = calendar.getTime();
        return format.format(date);
    }

    public String getLastDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        date = calendar.getTime();
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        date = calendar.getTime();
        return format.format(date);
    }

}
