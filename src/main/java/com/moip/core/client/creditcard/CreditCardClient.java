package com.moip.core.client.creditcard;

import java.math.BigDecimal;

import com.moip.core.model.Card;
import com.moip.core.model.PaymentStatus;

import feign.Headers;
import feign.RequestLine;
import lombok.AllArgsConstructor;
import lombok.Data;

public interface CreditCardClient {

	@RequestLine("POST /api/payments")
	@Headers("Content-Type: application/json")
	CreditCardPaymentResponse newPayment(BigDecimal transactionAmount, Card card);

	@Data
	@AllArgsConstructor
	class CreditCardPaymentResponse {
		
		private String authorizationCode;
		private PaymentStatus paymentStatus;
	}
}
