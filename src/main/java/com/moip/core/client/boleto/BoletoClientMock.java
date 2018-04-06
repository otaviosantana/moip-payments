package com.moip.core.client.boleto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Component;

@Component
public class BoletoClientMock implements BoletoClient {

	private static final String BOLETO_CODE_TEMPLATE = "34190.12345 93834.323462 43412.143134 5 %s%s";
	private static final LocalDate BASE_DATE = LocalDate.of(1997, 1, 1);

	@Override
	public BoletoPaymentResponse newPayment(BigDecimal amount, LocalDate dueDate) {
		String formattedAmount = String.format("%010d", amount.multiply(new BigDecimal("100")).intValue());
		String dueDateFormatted = String.format("%04d", ChronoUnit.DAYS.between(BASE_DATE, dueDate));
		
		return new BoletoPaymentResponse(String.format(BOLETO_CODE_TEMPLATE, dueDateFormatted, formattedAmount));
	}
}
