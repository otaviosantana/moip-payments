package com.moip.core.service;

import org.springframework.stereotype.Component;

import com.moip.core.model.PaymentResponse;
import com.moip.core.model.Transaction;

@Component
public class BoletoTransactionStrategy implements TransactionStrategy {

    @Override
    public PaymentResponse processPayment(Transaction transaction) {
        return null;
    }
}
