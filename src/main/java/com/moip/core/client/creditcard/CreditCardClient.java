package com.moip.core.client.creditcard;

import feign.Headers;
import feign.RequestLine;
import lombok.AllArgsConstructor;
import lombok.Data;

public interface CreditCardClient {

	@RequestLine("POST /api/payments")
	@Headers("Content-Type: application/json")
	CreditCardPaymentResponse newPayment();

	@Data
	@AllArgsConstructor
	class CreditCardPaymentResponse {
		
		private String authorizationCode;
		private CreditCardPaymentStatus paymentStatus;
	}

	enum CreditCardPaymentStatus {
		APPROVED,
		REPROVED,
		PENDING
	}
}
