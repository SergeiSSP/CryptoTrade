package com.senkiv.trade_bot.trader;

import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.market.Candlestick;
import com.binance.api.client.domain.market.CandlestickInterval;
import com.senkiv.trade_bot.util.Util;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ObserverImpl implements Observer{
    private final BinanceApiRestClient client;
    public ObserverImpl(BinanceApiRestClient client) {
        this.client = client;
    }

    @Override
    public Candlestick getCurrentInfo(String pair) {
        return client.getCandlestickBars(
                pair,
                CandlestickInterval.FIVE_MINUTES,
                1,
                null,
                null).getFirst();
    }

    @Override
    public Double getCurrentPrice(String pair) {
        return Double.parseDouble(client.getPrice(pair).getPrice());
    }

    @Override
    public List<Candlestick> getHistoricInfo(String pair, LocalDateTime from) {
        return client.getCandlestickBars(pair, TIME_FRAME, 500, Util.dateToLong(from), null);
    }

    @Override
    public List<Candlestick> getLastHistoricInfo(String pair) {
        return client.getCandlestickBars(pair, TIME_FRAME, Trader.CANDLE_OFFSET, null, null);
    }
}
