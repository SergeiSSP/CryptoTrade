package com.senkiv.tradeBot.trader;

import com.binance.api.client.BinanceApiRestClient;
import org.springframework.stereotype.Component;

@Component
class Account {
    private BinanceApiRestClient client;
    private int currentStake;
    private int openTrades;

    Account(BinanceApiRestClient client){
        this.client = client;
    }
}
