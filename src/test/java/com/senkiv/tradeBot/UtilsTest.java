package com.senkiv.tradeBot;

import org.junit.jupiter.api.Test;

import java.time.*;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UtilsTest {
    @Test
    void shouldCalculateTimeAndDateFromEpoch(){
        String result = Util.longToDate(1697925600000L);
        assertEquals("2023-10-22 00:00:00", result);
    }

    @Test
    void shouldCalculateEpochFromDateTime(){
        var dateTime = LocalDateTime.parse("2023-10-22T00:00:00");
        long result = Util.dateToLong(dateTime);
        assertAll(
                ()->assertEquals(2023, dateTime.getYear()),
                ()->assertEquals(Month.OCTOBER, dateTime.getMonth()),
                ()->assertEquals(22, dateTime.getDayOfMonth()),
                ()->assertEquals(00, dateTime.getHour()),
                ()->assertEquals(00, dateTime.getMinute()),
                ()->assertEquals(00, dateTime.getSecond()),
                ()->assertEquals(1697925600000L, OffsetDateTime.of(dateTime, ZoneOffset.of("+2")).toInstant().toEpochMilli())
        );

    }
}
