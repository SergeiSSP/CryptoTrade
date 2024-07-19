package com.senkiv.tradeBot.strategy;

import lombok.Getter;
import lombok.Setter;

public abstract class AbstractStrategy {
    @Getter
    @Setter
    private boolean isDryRun;
    @Getter
    @Setter
    private int timeFrame;

    public AbstractStrategy(boolean isDryRun, int timeFrame){
        this.isDryRun = isDryRun;
        this.timeFrame = timeFrame;
    }



    abstract public boolean buyIndicator();

    abstract public boolean sellIndicator();


}
