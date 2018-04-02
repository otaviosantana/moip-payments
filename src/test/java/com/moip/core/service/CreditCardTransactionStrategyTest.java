package com.moip.core.service;

import org.junit.Before;
import org.junit.Test;

import com.moip.core.model.Transaction;
import com.moip.fixture.TransactionFixture;

import br.com.six2six.fixturefactory.Fixture;

public class CreditCardTransactionStrategyTest {

    private CreditCardTransactionStrategy strategy;

    @Before
    public void setUp() throws Exception {
        TransactionFixture.setup();
        strategy = new CreditCardTransactionStrategy(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPayBoletoWithCreditCardStrategy() throws Exception {
        Transaction transaction = Fixture.from(Transaction.class).gimme(TransactionFixture.VALID_PAYMENT_WITH_BOLETO);
        strategy.processPayment(transaction);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testPayWithoutCardOnCreditCardStrategy() throws Exception {
        Transaction transaction = Fixture.from(Transaction.class).gimme(TransactionFixture.PAYMENT_WITHOUT_CARD);
        strategy.processPayment(transaction);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testPayWithInvalidCardNumberOnCreditCardStrategy() throws Exception {
        Transaction transaction = Fixture.from(Transaction.class).gimme(TransactionFixture.INVALID_TRANSACTION_WITH_INVALID_CARDNUMBER);
        strategy.processPayment(transaction);
    }
}
