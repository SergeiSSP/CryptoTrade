package com.senkiv.trade_bot;

import com.senkiv.trade_bot.trader.Executor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TradeBotApplication {

	public static void main(String[] args) {
		var context = SpringApplication.run(TradeBotApplication.class, args);
		var executor = context.getBean(Executor.class);
		executor.execute();
	}

}
