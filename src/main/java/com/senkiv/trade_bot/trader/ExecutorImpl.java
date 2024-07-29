package com.senkiv.trade_bot.trader;

import com.senkiv.trade_bot.util.IntervalMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


@Service
@Slf4j
public class  ExecutorImpl implements Executor{
    private final Trader trader;
    private final ScheduledExecutorService pool = new ScheduledThreadPoolExecutor(2);

    public ExecutorImpl(Trader trader) {
        this.trader = trader;
    }

    @Override
    public void execute() {
        log.info("Starting initization");
        trader.init();
        log.info("Completed initization");
        log.info("Starting trader");
        pool.scheduleWithFixedDelay(
                (Runnable) trader,
                IntervalMapper.toDuration(Observer.TIME_FRAME).toMinutes(),
                IntervalMapper.toDuration(Observer.TIME_FRAME).toMinutes(),
                TimeUnit.MINUTES
        );

    }
}
