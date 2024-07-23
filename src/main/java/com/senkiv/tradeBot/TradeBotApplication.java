package com.senkiv.tradeBot;

import com.binance.api.client.domain.market.Candlestick;
import com.senkiv.tradeBot.trader.Pair;
import com.senkiv.tradeBot.trader.SimpleBot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TradeBotApplication {

	public static void main(String[] args) {
		var context = SpringApplication.run(TradeBotApplication.class, args);
		var bean = context.getBean(SimpleBot.class);
		Candlestick res = bean.getCurrentInfo(Pair.BTCUSDT.getPair());
		System.out.println(res);
	}

}
