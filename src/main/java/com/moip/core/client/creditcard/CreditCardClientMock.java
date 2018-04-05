package com.moip.core.client.creditcard;

import java.math.BigDecimal;
import java.security.SecureRandom;

import org.springframework.stereotype.Component;

import com.moip.core.model.Card;
import com.moip.core.model.PaymentStatus;

import lombok.Getter;

@Component
public class CreditCardClientMock implements CreditCardClient {

	@Getter
	private CreditResponseType creditResponseType = CreditResponseType.APPROVED;

	@Override
	public CreditCardPaymentResponse newPayment(BigDecimal transactionAmount, Card card) {
		String authorizationCode = null;
		if (creditResponseType == CreditResponseType.APPROVED) {
			authorizationCode = String.valueOf(new SecureRandom().nextInt(1_000_000));
		}
		return new CreditCardPaymentResponse(authorizationCode, creditResponseType.getPaymentStatus());
	}

	public void approveResponse() {
		creditResponseType = CreditResponseType.APPROVED;
	}

	public void reproveResponse() {
		creditResponseType = CreditResponseType.REPROVED;
	}

	public void timeoutResponse() {
		creditResponseType = CreditResponseType.TIMEOUT;
	}

	@Getter
	public enum CreditResponseType {
		APPROVED(PaymentStatus.APPROVED),
		REPROVED(PaymentStatus.REPROVED),
		TIMEOUT(PaymentStatus.PENDING);

		private final PaymentStatus paymentStatus;

		private CreditResponseType(PaymentStatus paymentStatus) {
			this.paymentStatus = paymentStatus;
		}
	}
}
