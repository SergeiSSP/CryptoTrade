package com.senkiv.trade_bot.trader;

import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseStrategy;
import org.ta4j.core.Rule;
import org.ta4j.core.Strategy;
import org.ta4j.core.indicators.RSIIndicator;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;
import org.ta4j.core.rules.CrossedDownIndicatorRule;
import org.ta4j.core.rules.CrossedUpIndicatorRule;

/*
This class is instanced for each trading pair.
 */
public class TechnicalIndicatorCalculatorImpl implements TechnicalIndicatorCalculator {
    private final BarSeries barSeries;
    private final ClosePriceIndicator closePriceIndicator;
    private final RSIIndicator rsiIndicator;
    private final Rule entryRule;
    private final Rule exitRule;
    private final Strategy strategy;

    public TechnicalIndicatorCalculatorImpl(BarSeries barSeries) {
        this.barSeries = barSeries;
        this.closePriceIndicator = new ClosePriceIndicator(barSeries);
        this.rsiIndicator = new RSIIndicator(closePriceIndicator, 20);
        this.entryRule = new CrossedDownIndicatorRule(rsiIndicator, 30);
        this.exitRule = new CrossedUpIndicatorRule(rsiIndicator, 70);
        this.strategy = new BaseStrategy(entryRule, exitRule);
    }
    @Override
    public boolean toOpenOrder() {
        return strategy.shouldEnter(barSeries.getEndIndex());
    }

    @Override
    public boolean toCloseOrder() {
        return strategy.shouldExit(barSeries.getEndIndex());
    }

    @Override
    public BarSeries getBarSeries() {
        return barSeries;
    }
}
