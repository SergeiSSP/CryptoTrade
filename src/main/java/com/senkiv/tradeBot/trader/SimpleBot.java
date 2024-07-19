package com.senkiv.tradeBot.trader;

import com.binance.api.client.BinanceApiRestClient;
import com.senkiv.tradeBot.strategy.AbstractStrategy;
import org.springframework.stereotype.Component;

@Component
public class SimpleBot implements Observator{
    private AbstractStrategy strategy;
    private final BinanceApiRestClient client;
    SimpleBot(BinanceApiRestClient client){
        this.client = client;
    }
    @Override
    public String getInfo(String pair) {
        return client.getPrice(Pairs.BTCUSDT.getPair()).getPrice();
    }
}
