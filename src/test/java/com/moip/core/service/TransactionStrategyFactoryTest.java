package com.moip.core.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import static org.mockito.internal.util.reflection.Whitebox.setInternalState;

import com.moip.core.model.PaymentType;

public class TransactionStrategyFactoryTest {

	private TransactionStrategyFactory factory;

	@Mock
    private CreditCardTransactionStrategy creditCardTransactionStrategy;
	@Mock
    private BoletoTransactionStrategy boletoTransactionStrategy;

    @Before
	public void setUp() throws Exception {
    	initMocks(this);
		factory = new TransactionStrategyFactory();
		setInternalState(factory, "creditCardTransactionStrategy", creditCardTransactionStrategy);
		setInternalState(factory, "boletoTransactionStrategy", boletoTransactionStrategy);
		factory.initStrategies();
	}

	@Test
	public void testIfReturnsCorrectStrategyForEachPaymentType() throws Exception {
		assertTrue(factory.getStrategy(PaymentType.BOLETO) instanceof BoletoTransactionStrategy);
		assertTrue(factory.getStrategy(PaymentType.CREDIT_CARD) instanceof CreditCardTransactionStrategy);
	}
}
