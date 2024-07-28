package com.senkiv.trade_bot.trader;

import org.ta4j.core.BarSeries;

public interface TechnicalIndicatorCalculator {
    BarSeries getBarSeries();
    boolean toOpenOrder();
    boolean toCloseOrder();
}
