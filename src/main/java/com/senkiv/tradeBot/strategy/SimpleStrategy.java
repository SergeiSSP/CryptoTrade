package com.senkiv.tradeBot.strategy;

import lombok.Builder;

@Builder
public class SimpleStrategy extends AbstractStrategy{

    @Override
    public boolean buyIndicator() {
        return false;
    }

    @Override
    public boolean sellIndicator() {
        return false;
    }
}
