package com.senkiv.trade_bot.util;

import com.binance.api.client.domain.market.Candlestick;
import com.senkiv.trade_bot.trader.ObserverImpl;
import org.ta4j.core.Bar;
import org.ta4j.core.BaseBar;
import org.ta4j.core.BaseBarSeries;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.List;

import static java.time.ZoneOffset.UTC;

public class Util {
    private Util(){}

    public static LocalDateTime longToDate(long timestamp){
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.of("+2"));
    }

    public static long dateToLong(LocalDateTime dateTime){
        return dateTime.toInstant(UTC).toEpochMilli();
    }

    public static BaseBar convertCandlestickToBaseBar(Candlestick candlestick){
        return  new BaseBar(
                IntervalMapper.toDuration(ObserverImpl.TIME_FRAME),
                ZonedDateTime.of(longToDate(candlestick.getCloseTime()), ZoneId.of("+2")),
                Double.parseDouble(candlestick.getOpen()),
                Double.parseDouble(candlestick.getHigh()),
                Double.parseDouble(candlestick.getLow()),
                Double.parseDouble(candlestick.getLow()),
                Double.parseDouble(candlestick.getClose()),
                Double.parseDouble(candlestick.getVolume())
            );


    }

    public static BaseBarSeries convertListCandlesticksToBaseBarSeries(List<Candlestick> candlesticks){
        var barSeries = new BaseBarSeries();
        candlesticks.parallelStream()
                .forEach(candle -> barSeries.addBar(convertCandlestickToBaseBar(candle)));
        barSeries.getBarData().sort(Comparator.comparing(Bar::getBeginTime));
        return barSeries;
    }


}
