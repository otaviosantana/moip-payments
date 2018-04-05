package com.moip.core.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.moip.core.client.creditcard.CreditCardClient;
import com.moip.core.client.creditcard.CreditCardClient.CreditCardPaymentResponse;
import com.moip.core.model.Payment;
import com.moip.core.model.PaymentResponse;
import com.moip.core.model.PaymentStatus;
import com.moip.core.model.Transaction;
import com.moip.core.repository.TransactionRepository;
import com.moip.fixture.TransactionFixture;

import br.com.six2six.fixturefactory.Fixture;

public class CreditCardTransactionStrategyTest {

    private static final String AUTHORIZATION_CODE = "123456";

	private CreditCardTransactionStrategy strategy;

    @Mock
    private TransactionRepository transactionRepository;
    @Mock
    private CreditCardClient creditCardClient;

    @Before
    public void setUp() throws Exception {
    	TransactionFixture.setup();
    	initMocks(this);
        strategy = new CreditCardTransactionStrategy(transactionRepository, creditCardClient);
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

    @Test
    public void testWithApproveStatus() throws Exception {
    	Transaction transaction = Fixture.from(Transaction.class).gimme(TransactionFixture.VALID_WITH_CARD);
    	CreditCardPaymentResponse paymentResponse = new CreditCardPaymentResponse(AUTHORIZATION_CODE, PaymentStatus.APPROVED);
    	Payment payment = transaction.getPayment();
		when(creditCardClient.newPayment(payment.getAmount(), payment.getCard())).thenReturn(paymentResponse);

		PaymentResponse result = strategy.processPayment(transaction);

		assertEquals(AUTHORIZATION_CODE, payment.getCard().getAuthorizationCode());
		assertEquals(PaymentStatus.APPROVED, result.getStatus());
		verify(transactionRepository, times(2)).save(transaction);
    }

    @Test
    public void testWithReproveStatus() throws Exception {
    	Transaction transaction = Fixture.from(Transaction.class).gimme(TransactionFixture.VALID_WITH_CARD);
    	CreditCardPaymentResponse paymentResponse = new CreditCardPaymentResponse(null, PaymentStatus.REPROVED);
    	Payment payment = transaction.getPayment();
		when(creditCardClient.newPayment(payment.getAmount(), payment.getCard())).thenReturn(paymentResponse);

		PaymentResponse result = strategy.processPayment(transaction);

		assertNull(payment.getCard().getAuthorizationCode());
		assertEquals(PaymentStatus.REPROVED, result.getStatus());
		verify(transactionRepository, times(2)).save(transaction);
    }

    @Test
    public void testWithPendingStatus() throws Exception {
    	Transaction transaction = Fixture.from(Transaction.class).gimme(TransactionFixture.VALID_WITH_CARD);
    	CreditCardPaymentResponse paymentResponse = new CreditCardPaymentResponse(null, PaymentStatus.PENDING);
    	Payment payment = transaction.getPayment();
		when(creditCardClient.newPayment(payment.getAmount(), payment.getCard())).thenReturn(paymentResponse);

		PaymentResponse result = strategy.processPayment(transaction);

		assertNull(payment.getCard().getAuthorizationCode());
		assertEquals(PaymentStatus.PENDING, result.getStatus());
		verify(transactionRepository, times(2)).save(transaction);
    }
}
