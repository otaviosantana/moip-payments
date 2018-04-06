package com.moip.core.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.mockito.internal.util.reflection.Whitebox.setInternalState;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.moip.core.client.boleto.BoletoClient;
import com.moip.core.client.boleto.BoletoClient.BoletoPaymentResponse;
import com.moip.core.model.Payment;
import com.moip.core.model.PaymentResponse;
import com.moip.core.model.PaymentStatus;
import com.moip.core.model.Transaction;
import com.moip.fixture.TransactionFixture;

import br.com.six2six.fixturefactory.Fixture;

public class BoletoTransactionStrategyTest {

	private static final Long DAYS_TO_DUE_DATE = new Long(2);
	private static final String BAR_CODE = "34190.12345 93834.323462 43412.143134 5 61970000000000";

	@Mock
	private BoletoClient boletoClient;

	private BoletoTransactionStrategy strategy;

	@Before
	public void setUp() throws Exception {
		Clock clock = Clock.fixed(Instant.parse("1910-09-01T00:00:00.00Z"), ZoneOffset.UTC);
		TransactionFixture.setup();
		initMocks(this);
		strategy = new BoletoTransactionStrategy(boletoClient, clock);
		setInternalState(strategy, "daysToDueDate", DAYS_TO_DUE_DATE);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testProcessPaymentWithInvalidPaymentType() throws Exception {
		Transaction transaction = Fixture.from(Transaction.class).gimme(TransactionFixture.VALID_WITH_CARD);
		strategy.processPayment(transaction);
	}

	@Test
	public void testProcessPayment() throws Exception {
		Transaction transaction = Fixture.from(Transaction.class).gimme(TransactionFixture.VALID_WITH_BOLETO);
		Payment payment = transaction.getPayment();
		LocalDate dueDate = LocalDate.of(1910, 9, 3);
		when(boletoClient.newPayment(payment.getAmount(), dueDate)).thenReturn(new BoletoPaymentResponse(BAR_CODE));
		PaymentResponse result = strategy.processPayment(transaction);

		assertEquals(PaymentStatus.PENDING, result.getStatus());
		assertEquals(BAR_CODE, result.getValue());
	}
}
