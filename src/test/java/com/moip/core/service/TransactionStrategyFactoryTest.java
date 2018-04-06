package com.moip.core.service;

import static org.junit.Assert.assertEquals;
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
		assertEquals(BoletoTransactionStrategy.class, factory.getStrategy(PaymentType.BOLETO).getClass());
		assertEquals(CreditCardTransactionStrategy.class, factory.getStrategy(PaymentType.CREDIT_CARD).getClass());
	}
}
