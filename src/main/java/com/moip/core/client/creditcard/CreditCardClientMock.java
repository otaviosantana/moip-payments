package com.moip.core.client.creditcard;

import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
public class CreditCardClientMock implements CreditCardClient {

	@Getter
	private CreditResponseType creditResponseType = CreditResponseType.APPROVED;

	@Override
	public CreditCardPaymentResponse newPayment() {
		return new CreditCardPaymentResponse("authorizationCode", creditResponseType.getPaymentStatus());
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
		APPROVED(CreditCardPaymentStatus.APPROVED),
		REPROVED(CreditCardPaymentStatus.REPROVED),
		TIMEOUT(CreditCardPaymentStatus.PENDING);

		private final CreditCardPaymentStatus paymentStatus;

		private CreditResponseType(CreditCardPaymentStatus paymentStatus) {
			this.paymentStatus = paymentStatus;
		}
	}
}
