package com.senkiv.trade_bot.trader;

import com.senkiv.trade_bot.util.IntervalMapper;
import org.springframework.stereotype.Service;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


@Service
public class  ExecutorImpl implements Executor{
    private final Trader trader;
    private final ScheduledExecutorService pool = new ScheduledThreadPoolExecutor(2);

    public ExecutorImpl(Trader trader) {
        this.trader = trader;
    }

    @Override
    public void execute() {
        trader.init();
        pool.scheduleWithFixedDelay(
                (Runnable) trader,
                IntervalMapper.toDuration(Observer.TIME_FRAME).toMinutes(),
                IntervalMapper.toDuration(Observer.TIME_FRAME).toMinutes(),
                TimeUnit.MINUTES
        );

    }
}
