package com.moip.core.client.boleto;


import java.math.BigDecimal;
import java.time.LocalDate;

import feign.RequestLine;
import lombok.AllArgsConstructor;
import lombok.Data;

public interface BoletoClient {

	@RequestLine("POST /api/payments")
	BoletoPaymentResponse newPayment(BigDecimal amount, LocalDate dueDate);

	@Data
	@AllArgsConstructor
	class BoletoPaymentResponse {

		private String barCode;
	}
}
