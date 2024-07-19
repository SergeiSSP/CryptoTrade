package com.senkiv.tradeBot.trader;

public enum Pairs {
    BTCUSDT("BTCUSDT");

    private final String pair;

    Pairs(String pair) {
        this.pair = pair;
    }

    public String getPair(){
        return pair;
    }
}
