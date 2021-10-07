package com.andersonjunior.calltick.component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataConverter {

    public Date parseDate(String data) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setLenient(false);
        return format.parse(data);
    }
    
}
