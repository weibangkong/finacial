package com.kwb.saller;

import java.text.*;
import java.util.Date;

public class MyDateFormate extends DateFormat{
    private DateFormat dateFormat;
    private SimpleDateFormat sdf = new SimpleDateFormat();

    public MyDateFormate(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    @Override
    public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {
        return dateFormat.format(date,toAppendTo,fieldPosition);
    }

    @Override
    public Date parse(String source, ParsePosition pos) {
        Date date = null;
        try {
            date = sdf.parse(source, pos);
        } catch (Exception e) {
            date = dateFormat.parse(source, pos);
        }
        return date;
    }


    public Date parse(String source) throws ParseException {
        Date date = null;
        try {
            date = sdf.parse(source);
        } catch (Exception e) {
            date = dateFormat.parse(source);
        }
        return date;
    }


    public Object clone() {
        Object formate = dateFormat.clone();
        return new MyDateFormate((DateFormat) formate);
    }

}
