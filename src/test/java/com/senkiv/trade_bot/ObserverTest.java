package com.senkiv.trade_bot;

import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.market.Candlestick;
import com.binance.api.client.domain.market.CandlestickInterval;
import com.binance.api.client.domain.market.TickerPrice;
import com.senkiv.trade_bot.trader.Observer;
import com.senkiv.trade_bot.trader.ObserverImpl;
import com.senkiv.trade_bot.trader.Trader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.*;


class ObserverTest {
    private static final String PAIR = "BTCUSDT";
    private static final LocalDateTime DATE_TIME = LocalDateTime.now();
    private static final CandlestickInterval TIME_FRAME = CandlestickInterval.FIVE_MINUTES;
    private BinanceApiRestClient client = Mockito.mock(BinanceApiRestClient.class);
    private Observer observer = new ObserverImpl(client);

    @BeforeEach
    public void setUp() {
        observer = new ObserverImpl(client);
    }

    @Test
    void testGetCurrentInfo() {
        Candlestick candlestick = new Candlestick();
        when(client.getCandlestickBars(eq(PAIR), eq(CandlestickInterval.FIVE_MINUTES), eq(1), isNull(), isNull()))
                .thenReturn(Collections.singletonList(candlestick));

        Candlestick result = observer.getCurrentInfo(PAIR);

        assertEquals(candlestick, result);
        verify(client, times(1)).getCandlestickBars(eq(PAIR), eq(CandlestickInterval.FIVE_MINUTES), eq(1), isNull(), isNull());
    }

    @Test
    void testGetCurrentPrice() {
        TickerPrice tickerPrice = new TickerPrice();
        tickerPrice.setPrice("12345.67");
        when(client.getPrice(eq(PAIR))).thenReturn(tickerPrice);

        Double result = observer.getCurrentPrice(PAIR);

        Assertions.assertEquals((Double) 12345.67, result);
        verify(client, times(1)).getPrice(PAIR);
    }

    @Test
    void testGetHistoricInfo() {
        Candlestick candlestick = new Candlestick();
        List<Candlestick> candlesticks = Collections.singletonList(candlestick);
        when(client.getCandlestickBars(eq(PAIR), eq(TIME_FRAME), eq(500), anyLong(), isNull()))
                .thenReturn(candlesticks);

        List<Candlestick> result = observer.getHistoricInfo(PAIR, DATE_TIME);

        assertEquals(candlesticks, result);
        verify(client, times(1)).getCandlestickBars(eq(PAIR), eq(TIME_FRAME), eq(500), anyLong(), isNull());
    }

    @Test
    void testGetLastHistoricInfo() {
        Candlestick candlestick = new Candlestick();
        List<Candlestick> candlesticks = Collections.singletonList(candlestick);
        when(client.getCandlestickBars(eq(PAIR), eq(TIME_FRAME), eq(Trader.CANDLE_OFFSET), isNull(), isNull()))
                .thenReturn(candlesticks);

        List<Candlestick> result = observer.getLastHistoricInfo(PAIR);

        assertEquals(candlesticks, result);
        verify(client, times(1)).getCandlestickBars(eq(PAIR), eq(TIME_FRAME), eq(Trader.CANDLE_OFFSET), isNull(), isNull());
    }

}
