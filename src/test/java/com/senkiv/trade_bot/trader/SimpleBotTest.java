package com.senkiv.trade_bot.trader;

import com.binance.api.client.domain.market.Candlestick;
import com.senkiv.trade_bot.entity.Pair;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SimpleBotTest {

    @Autowired
    Observer observer;
    @Test
    public void shouldReturn500ItemsInList(){
        List<Candlestick> list = observer.getHistoricInfo(Pair.BTCUSDT.getPair(), LocalDateTime.of(2024, 1, 1, 10, 10));
        assertEquals(500, list.size());
    }
}
