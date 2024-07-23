package com.senkiv.tradeBot.trader;

import lombok.Getter;

@Getter
public enum Pair {
    BTCUSDT("BTCUSDT");

    private final String pair;

    Pair(String pair) {
        this.pair = pair;
    }

}
