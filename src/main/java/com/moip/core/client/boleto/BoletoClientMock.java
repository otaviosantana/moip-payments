package com.moip.core.client.boleto;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.joda.time.DateTime;
import org.joda.time.Days;

public class BoletoClientMock implements BoletoClient {

	private static final String BOLETO_CODE_TEMPLATE = "34190.12345 93834.323462 43412.143134 5 %s%s";
	private static final DateTime BASE_DATE = new DateTime(LocalDate.of(1997, 1, 1));

	@Override
	public BoletoPaymentResponse newPayment(BigDecimal amount, LocalDate dueDate) {
		String formattedAmount = String.format("%10d", amount.multiply(new BigDecimal("100")).intValue());
		Days daysBetween = Days.daysBetween(new DateTime(dueDate), BASE_DATE);
		return new BoletoPaymentResponse(String.format(BOLETO_CODE_TEMPLATE, daysBetween.getDays(), formattedAmount));
	}
}
