package com.senkiv.trade_bot.strategy;

import lombok.Builder;
import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseBarSeries;
import org.ta4j.core.Rule;
import org.ta4j.core.indicators.RSIIndicator;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;
import org.ta4j.core.rules.CrossedDownIndicatorRule;
import org.ta4j.core.rules.CrossedUpIndicatorRule;

@Builder
public class SimpleStrategy extends AbstractStrategy{
    private final BarSeries barSeries = new BaseBarSeries();
    private final RSIIndicator rsiIndicator = new RSIIndicator(new ClosePriceIndicator(barSeries), 10);

    @Override
    public boolean buyIndicator() {
        Rule rule = new CrossedDownIndicatorRule(rsiIndicator,30);
        return rule.isSatisfied(barSeries.getEndIndex());
    }

    @Override
    public boolean sellIndicator() {
        Rule rule = new CrossedUpIndicatorRule(rsiIndicator,70);
        return rule.isSatisfied(barSeries.getEndIndex());
    }
}
