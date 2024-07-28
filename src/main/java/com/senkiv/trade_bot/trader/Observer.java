package com.senkiv.trade_bot.trader;

import com.binance.api.client.domain.market.Candlestick;
import com.binance.api.client.domain.market.CandlestickInterval;

import java.time.LocalDateTime;
import java.util.List;

public interface Observer {
    CandlestickInterval TIME_FRAME = CandlestickInterval.FIFTEEN_MINUTES;
    Candlestick getCurrentInfo(String pair);
    List<Candlestick> getHistoricInfo(String pair, LocalDateTime from);
    List<Candlestick> getLastHistoricInfo(String pair);
    Double getCurrentPrice(String pair);


}
