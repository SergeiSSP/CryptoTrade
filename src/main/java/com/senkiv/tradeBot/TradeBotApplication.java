package com.senkiv.tradeBot;

import com.senkiv.tradeBot.trader.SimpleBot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TradeBotApplication {

	public static void main(String[] args) {
		var context = SpringApplication.run(TradeBotApplication.class, args);
		var bean = context.getBean(SimpleBot.class);
		String res = bean.getInfo("BTCUSDT");
		System.out.println(res);
	}

}
