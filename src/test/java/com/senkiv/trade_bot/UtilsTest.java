package com.senkiv.trade_bot;

import com.senkiv.trade_bot.util.Util;
import org.junit.jupiter.api.Test;

import java.time.*;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UtilsTest {
    @Test
    void shouldCalculateTimeAndDateFromEpoch(){
        String result = Util.longToDate(1697925600000L).toString();
        assertEquals("2023-10-22T00:00", result);
    }

    @Test
    void shouldCalculateEpochFromDateTime(){
        var dateTime = LocalDateTime.parse("2023-10-22T00:00:00");
        assertAll(
                ()->assertEquals(2023, dateTime.getYear()),
                ()->assertEquals(Month.OCTOBER, dateTime.getMonth()),
                ()->assertEquals(22, dateTime.getDayOfMonth()),
                ()->assertEquals(0, dateTime.getHour()),
                ()->assertEquals(0, dateTime.getMinute()),
                ()->assertEquals(0, dateTime.getSecond()),
                ()->assertEquals(1697925600000L, OffsetDateTime.of(dateTime, ZoneOffset.of("+2")).toInstant().toEpochMilli())
        );

    }
}
