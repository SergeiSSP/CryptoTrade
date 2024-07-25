package com.senkiv.trade_bot.trader;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import com.binance.api.client.domain.account.Account;
import com.binance.api.client.domain.account.NewOrder;
import com.binance.api.client.domain.account.NewOrderResponse;
import com.senkiv.trade_bot.Util;

import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.market.Candlestick;
import com.binance.api.client.domain.market.CandlestickInterval;
import com.senkiv.trade_bot.entity.Pair;
import com.senkiv.trade_bot.strategy.AbstractStrategy;
import com.senkiv.trade_bot.telegram.TelegramDispatcher;
import org.springframework.stereotype.Service;


@Service
public class SimpleBot implements Observer, Trader{
    private static final double STAKE_PART_FOR_TRADE = 0.1;
    private static final double FIXED_STAKE = 10;
    private final TelegramDispatcher telegramDispatcher;
    private AbstractStrategy strategy;
    private final BinanceApiRestClient client;
    private final Map<Pair, List<Candlestick>> workingPairs = new EnumMap<>(Pair.class);
    private final Map<Pair, NewOrderResponse> openedOrders = new EnumMap<>(Pair.class);
    private final Account account = new Account();

     public SimpleBot(BinanceApiRestClient client, TelegramDispatcher telegramDispatcher){
        this.client = client;
        this.telegramDispatcher = telegramDispatcher;
    }

    @Override
    public Candlestick getCurrentInfo(String pair) {
        return client.getCandlestickBars(pair, CandlestickInterval.FIVE_MINUTES, 1, null, null).getFirst();
    }

    @Override
    public Double getCurrentPrice(String pair) {
        return Double.parseDouble(client.getPrice(pair).getPrice());
    }

    @Override
    public List<Candlestick> getHistoricInfo(String pair, LocalDateTime from) {
        return client.getCandlestickBars(pair, strategy.getTimeFrame(), 500, Util.dateToLong(from), null);
    }

    @Override
    public List<Candlestick> getLastHistoricInfo(String pair) {
        return client.getCandlestickBars(pair, strategy.getTimeFrame());
    }

    @Override
    public NewOrderResponse executeOpenOrder(Pair pair) {
        NewOrder order = NewOrder.marketBuy(pair.getPair(), String.valueOf(calculateAmountOfAssetToBuy(pair)));
        var response = client.newOrder(order);
        openedOrders.put(pair, response);
        return response;
    }


    @Override
    public NewOrderResponse executeCloseOrder(Pair pair) {
        var order = NewOrder.marketSell(pair.getPair(), openedOrders.get(pair).getOrigQty());
        return client.newOrder(order);
    }

    private double getUSDTBalance(){
        return Double.parseDouble(account.getAssetBalance("USDT").getFree());
    }

    /* Not used yet */
    private double getStakeForOrder(){
         return getUSDTBalance() * STAKE_PART_FOR_TRADE;
    }

    private double getFixedStakeForOrder(){
        var available = getUSDTBalance();
         return Math.min(available, FIXED_STAKE);
    }

    private double calculateAmountOfAssetToBuy(Pair pair){
        return FIXED_STAKE / getCurrentPrice(pair.getPair()) * 100;
    }

    public void addWorkingPair(Pair pair){
        workingPairs.put(pair, new ArrayList<Candlestick>());
    }


}
