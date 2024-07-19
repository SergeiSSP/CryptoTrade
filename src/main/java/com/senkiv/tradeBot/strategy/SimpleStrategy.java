package com.senkiv.tradeBot.strategy;

import org.springframework.boot.autoconfigure.task.TaskExecutionProperties;

public class SimpleStrategy extends AbstractStrategy{
    public SimpleStrategy(boolean isDryRun, int timeFrame) {
        super(isDryRun, timeFrame);
    }

    @Override
    public boolean buyIndicator() {
        return false;
    }

    @Override
    public boolean sellIndicator() {
        return false;
    }
}
