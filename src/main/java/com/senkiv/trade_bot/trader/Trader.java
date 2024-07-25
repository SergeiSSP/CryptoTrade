package com.senkiv.trade_bot.trader;

import com.binance.api.client.domain.account.NewOrderResponse;
import com.senkiv.trade_bot.entity.Pair;

public interface Trader {
    NewOrderResponse executeOpenOrder(Pair pair);
    NewOrderResponse executeCloseOrder(Pair pair);
}
