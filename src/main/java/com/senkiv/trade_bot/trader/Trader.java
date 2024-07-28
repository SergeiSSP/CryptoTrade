package com.senkiv.trade_bot.trader;

import com.binance.api.client.domain.account.NewOrderResponse;
import com.senkiv.trade_bot.entity.Pair;

public interface Trader {
    int CANDLE_OFFSET = 20;
    void init();
    NewOrderResponse executeOpenOrder(Pair pair);
    NewOrderResponse executeCloseOrder(Pair pair);
}
