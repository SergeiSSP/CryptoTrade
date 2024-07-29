package com.senkiv.trade_bot.trader;

import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.account.Account;
import com.binance.api.client.domain.account.NewOrder;
import com.binance.api.client.domain.account.NewOrderResponse;
import com.senkiv.trade_bot.entity.Pair;
import com.senkiv.trade_bot.util.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

import static com.senkiv.trade_bot.util.Util.mapNewOrderToNewOrderResponse;

@Service
@Slf4j
public final class TraderImpl implements Trader, Runnable {
    private static final String BASE_CURRENCY = "USDT";
    private static final double FIXED_STAKE = 10;
    private static final boolean IS_DRY_RUN = true; /* Change for real trading */
    private final Map<Pair, NewOrderResponse> openedOrders = new EnumMap<>(Pair.class);
    private final BinanceApiRestClient client;
    private final Account account;
    private final Observer observer;
    private final Map<Pair, TechnicalIndicatorCalculator> calculators = new EnumMap<>(Pair.class);

    public TraderImpl(BinanceApiRestClient client, Account account, Observer observer) {
        this.client = client;
        this.account = account;
        this.observer = observer;
    }

    @Override
    public NewOrderResponse executeOpenOrder(Pair pair) {
        NewOrderResponse response;
        NewOrder order = NewOrder.marketBuy(pair.getPair(), String.valueOf(calculateAmountOfAssetToBuy(pair)));
        if (!IS_DRY_RUN) {
            response = client.newOrder(order);
        }else{
            response = mapNewOrderToNewOrderResponse(order);
        }
        openedOrders.put(pair, response);
        return response;
    }


    @Override
    public NewOrderResponse executeCloseOrder(Pair pair) {
        NewOrderResponse response;
        var order = NewOrder.marketSell(pair.getPair(), openedOrders.get(pair).getOrigQty());
        if(!IS_DRY_RUN){
            response = client.newOrder(order);
        }else{
            response = mapNewOrderToNewOrderResponse(order);
        }
        return response;
    }

    @Override
    public void run() {
        Arrays.stream(Pair.values())
                .parallel()
                .forEach(pair -> {
                    updateBarSeries(pair);
                    var calc = calculators.get(pair);
                    if (openedOrders.containsKey(pair)) {
                        if (calc.toCloseOrder()) {
                            var response = executeCloseOrder(pair);
                            log.info(Util.generateOrderReport(response));
                        }
                    } else {
                        if (calc.toOpenOrder()) {
                           var response = executeOpenOrder(pair);
                            log.info(Util.generateOrderReport(response));
                        }
                    }

                });
    }

    @Override
    public void init() {
        for (Pair pair : Pair.values()) {
            var candle = observer.getLastHistoricInfo(pair.getPair());
            var barSeries = Util.convertListCandlesticksToBaseBarSeries(candle);
            calculators.put(pair, new TechnicalIndicatorCalculatorImpl(barSeries));
        }
    }

    private void updateBarSeries(Pair pair) {
        calculators.get(pair).getBarSeries().addBar(Util.convertCandlestickToBaseBar(observer.getCurrentInfo(pair.getPair())));
    }

    private double getUSDTBalance() {
        return Double.parseDouble(account.getAssetBalance(BASE_CURRENCY).getFree());
    }

    private double getFixedStakeForOrder() {
        var available = getUSDTBalance();
        return Math.min(available, FIXED_STAKE);
    }

    private double calculateAmountOfAssetToBuy(Pair pair) {
        return getFixedStakeForOrder() / observer.getCurrentPrice(pair.getPair()) * 100;
    }
}
