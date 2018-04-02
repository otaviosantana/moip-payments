package com.moip.core.service;

import com.moip.core.model.PaymentResponse;
import com.moip.core.model.Transaction;

public interface TransactionStrategy {

    PaymentResponse processPayment(Transaction transaction);
}
