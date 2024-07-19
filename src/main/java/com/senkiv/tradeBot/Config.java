package com.senkiv.tradeBot;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
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

    @Bean
    BinanceApiRestClient client(){
        return BinanceApiClientFactory.newInstance(API_KEY, SECRET_KEY).newRestClient();
    }

}
