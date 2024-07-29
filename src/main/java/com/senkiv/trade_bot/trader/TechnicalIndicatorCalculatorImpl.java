package com.senkiv.trade_bot.trader;

import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseStrategy;
import org.ta4j.core.Rule;
import org.ta4j.core.Strategy;
import org.ta4j.core.indicators.RSIIndicator;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;
import org.ta4j.core.num.Num;
import org.ta4j.core.rules.CrossedDownIndicatorRule;
import org.ta4j.core.rules.CrossedUpIndicatorRule;

import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/*
This class is instanced for each trading pair.
 */
public class TechnicalIndicatorCalculatorImpl implements TechnicalIndicatorCalculator {
    private final BarSeries barSeries;
    private final RSIIndicator rsiIndicator;
    private final Strategy strategy;
    private static final Logger logger = Logger.getLogger(TechnicalIndicatorCalculatorImpl.class.getName());

    public TechnicalIndicatorCalculatorImpl(BarSeries barSeries) {
        this.barSeries = barSeries;
        ClosePriceIndicator closePriceIndicator = new ClosePriceIndicator(barSeries);
        this.rsiIndicator = new RSIIndicator(closePriceIndicator, 20);
        Rule entryRule = new CrossedDownIndicatorRule(rsiIndicator, 30);
        Rule exitRule = new CrossedUpIndicatorRule(rsiIndicator, 70);
        this.strategy = new BaseStrategy(entryRule, exitRule);
    }
    @Override
    public boolean toOpenOrder() {
        logger.log(new LogRecord(Level.FINE, getRsiIndicator().toString()));
        return strategy.shouldEnter(barSeries.getEndIndex());
    }

    @Override
    public boolean toCloseOrder() {
        logger.log(new LogRecord(Level.FINE, getRsiIndicator().toString()));
        return strategy.shouldExit(barSeries.getEndIndex());
    }

    @Override
    public BarSeries getBarSeries() {
        return barSeries;
    }

    public Num getRsiIndicator() {
        return rsiIndicator.getValue(barSeries.getEndIndex());
    }
}
