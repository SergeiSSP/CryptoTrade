package com.senkiv.tradeBot.trader;

import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.market.Candlestick;
import com.binance.api.client.domain.market.CandlestickInterval;
import com.senkiv.tradeBot.strategy.AbstractStrategy;
import com.senkiv.tradeBot.telegram.TelegramDispatcher;
import org.springframework.stereotype.Component;


import java.time.Instant;
import java.util.List;


@Component
public class SimpleBot implements Observator{
    private final TelegramDispatcher telegramDispatcher;
    private AbstractStrategy strategy;



    private final BinanceApiRestClient client;
    SimpleBot(BinanceApiRestClient client, TelegramDispatcher telegramDispatcher){
        this.client = client;
        this.telegramDispatcher = telegramDispatcher;
    }
    @Override
    public Candlestick getCurrentInfo(String pair) {
        return client.getCandlestickBars(pair, CandlestickInterval.FIVE_MINUTES, 1, null, null).getFirst();
    }

    @Override
    public List<Candlestick> getHistoricInfo(String pair, long timeStampFrom) {
        return null;
    }
}
