package com.senkiv.tradeBot;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

public class Util {
    public static String longToDate(long timestamp){
        Date date = new Date(timestamp);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(date);
    }

    public static long dateToLong(LocalDateTime dateTime){
        return dateTime.toInstant(ZoneOffset.UTC).toEpochMilli();
    }
}
