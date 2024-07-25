package com.senkiv.trade_bot;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.pengrad.telegrambot.TelegramBot;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

@Configuration
@PropertySource("classpath:secret.properties")
public class Config {
    @Value("${API_KEY}")
    private String API_KEY;
    @Value("${SECRET_KEY}")
    private String SECRET_KEY;
    @Value("${TELEGRAM_BOT_TOKEN}")
    private String TELEGRAM_BOT_KEY;

    @Bean
    @Scope(scopeName = "singleton")
    BinanceApiRestClient client(){
        return BinanceApiClientFactory.newInstance(API_KEY, SECRET_KEY).newRestClient();
    }

    @Bean
    @Scope(scopeName="singleton")
    TelegramBot telegram(){
        return new TelegramBot(TELEGRAM_BOT_KEY);
    }
}
