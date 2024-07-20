package com.senkiv.tradeBot.trader;

import lombok.Getter;

@Getter
public enum Pairs {
    BTCUSDT("BTCUSDT");

    private final String pair;

    Pairs(String pair) {
        this.pair = pair;
    }

}
