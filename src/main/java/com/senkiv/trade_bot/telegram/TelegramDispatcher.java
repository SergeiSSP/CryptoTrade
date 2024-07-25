package com.senkiv.trade_bot.telegram;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(scopeName = "singleton")
@PropertySource("classpath:secret.properties")
public class TelegramDispatcher {
    @Value("${CHAT_ID}")
    private long CHAT_ID;
    private final TelegramBot bot;

    public TelegramDispatcher(TelegramBot bot) {
        this.bot = bot;
    }

    public void sendMessage(String msg){
        bot.execute(new SendMessage(CHAT_ID, msg));
    }
}
