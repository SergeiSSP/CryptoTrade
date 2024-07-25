package com.senkiv.trade_bot.strategy;

import com.binance.api.client.domain.account.NewOrderResponse;
import com.binance.api.client.domain.account.Order;
import com.binance.api.client.domain.market.CandlestickInterval;
import com.senkiv.trade_bot.trader.Observer;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;


@Setter
@Getter
public abstract class AbstractStrategy {
    private boolean isDryRun;
    @NotNull
    private CandlestickInterval timeFrame;
    private int startOffSet;
    private float ROI;


    public abstract boolean buyIndicator();

    public abstract boolean sellIndicator();

    public double calculateCurrentROI(Observer observer, NewOrderResponse order) {
        return Double.parseDouble(order.getPrice()) / observer.getCurrentPrice(order.getSymbol());
    }
}
