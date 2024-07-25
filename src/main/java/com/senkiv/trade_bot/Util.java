package com.senkiv.trade_bot;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static java.time.ZoneOffset.UTC;

public class Util {
    public static String longToDate(long timestamp){
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.of("+2")).toString();
    }

    public static long dateToLong(LocalDateTime dateTime){
        return dateTime.toInstant(UTC).toEpochMilli();
    }
}
