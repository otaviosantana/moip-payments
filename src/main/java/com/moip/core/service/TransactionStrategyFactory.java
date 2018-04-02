package com.moip.core.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.moip.core.model.PaymentType;

@Component
public class TransactionStrategyFactory {

    @Autowired
    private CreditCardTransactionStrategy creditCardTransactionStrategy;
    @Autowired
    private BoletoTransactionStrategy boletoTransactionStrategy;
    private Map<PaymentType, TransactionStrategy> strategies;

    @PostConstruct
    private void initStrategies() {
        strategies = new HashMap<>();
        strategies.put(PaymentType.BOLETO, boletoTransactionStrategy);
        strategies.put(PaymentType.CREDIT_CARD, creditCardTransactionStrategy);
    }

    public TransactionStrategy getStrategy(PaymentType paymentType) {
        return strategies.get(paymentType);
    }
}
