package com.senkiv.tradeBot.trader;

import com.binance.api.client.BinanceApiRestClient;
import org.springframework.stereotype.Component;

@Component
public class Wallet {
    private BinanceApiRestClient client;
    private int currentStake;
    private int openTrades;
    private Pair displayCurrency;
    Wallet(BinanceApiRestClient client){
        this.client = client;
    }
}
