package com.moip.core.client.boleto;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import com.moip.core.client.boleto.BoletoClient.BoletoPaymentResponse;

public class BoletoClientMockTest {

	private static final String BASE_BAR_CODE = "34190.12345 93834.323462 43412.143134 5 ";

	private BoletoClientMock client;

	@Before
	public void setUp() {
		client = new BoletoClientMock();
	}

	@Test
	public void testWithDifferenceBetweenDueDateAndBaseDateLessThan1000() {
		LocalDate dueDate = LocalDate.of(1997, 1, 3);

		BoletoPaymentResponse result = client.newPayment(new BigDecimal("123.45"), dueDate);

		assertEquals(BASE_BAR_CODE + "00020000012345", result.getBarCode());
	}

	@Test
	public void testWithDifferenceBetweenDueDateAndBaseDateGreatherThan1000() {
		LocalDate dueDate = LocalDate.of(2001, 1, 1);

		BoletoPaymentResponse result = client.newPayment(new BigDecimal("123.45"), dueDate);

		assertEquals(BASE_BAR_CODE + "14610000012345", result.getBarCode());
	}
}
