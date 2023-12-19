package org.example.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtraOptions {
    public static String date = getFormattedDate();

    public String getDate() {
        return date;
    }

    public static String getFormattedDate() {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmss");
        return dateFormat.format(currentDate);
    }
}

