package com.senkiv.tradeBot.strategy;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class AbstractStrategy {
    private boolean isDryRun;
    private int timeFrame;
    private int startOffSet;

    public AbstractStrategy(boolean isDryRun, int timeFrame, int startOffSet){
        this.isDryRun = isDryRun;
        this.timeFrame = timeFrame;
        this.startOffSet = startOffSet;
    }

    abstract public boolean buyIndicator();

    abstract public boolean sellIndicator();


}
