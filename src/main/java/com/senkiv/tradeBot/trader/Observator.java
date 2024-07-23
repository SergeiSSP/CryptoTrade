package com.senkiv.tradeBot.trader;

import com.binance.api.client.domain.market.Candlestick;

import java.util.List;

public interface Observator {
    Candlestick getCurrentInfo(String pair);
    List<Candlestick> getHistoricInfo(String pair, long timeStampFrom);

}
