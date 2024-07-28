package com.senkiv.trade_bot.util;

import com.binance.api.client.domain.market.CandlestickInterval;

import java.time.Duration;

public final class IntervalMapper {
    private IntervalMapper() {}

    public static Duration toDuration(CandlestickInterval interval) {
        switch (interval) {
            case ONE_MINUTE:
                return Duration.ofMinutes(1);
            case THREE_MINUTES:
                return Duration.ofMinutes(3);
            case FIVE_MINUTES:
                return Duration.ofMinutes(5);
            case FIFTEEN_MINUTES:
                return Duration.ofMinutes(15);
            case HALF_HOURLY:
                return Duration.ofMinutes(30);
            case HOURLY:
                return Duration.ofHours(1);
            case TWO_HOURLY:
                return Duration.ofHours(2);
            case FOUR_HOURLY:
                return Duration.ofHours(4);
            case SIX_HOURLY:
                return Duration.ofHours(6);
            case EIGHT_HOURLY:
                return Duration.ofHours(8);
            case TWELVE_HOURLY:
                return Duration.ofHours(12);
            case DAILY:
                return Duration.ofDays(1);
            case THREE_DAILY:
                return Duration.ofDays(3);
            case WEEKLY:
                return Duration.ofDays(7);
            case MONTHLY:
                return Duration.ofDays(30); // Approximating 30 days for a month
            default:
                throw new IllegalArgumentException("Unsupported interval: " + interval);
        }
    }
}
