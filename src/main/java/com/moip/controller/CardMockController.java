package com.moip.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moip.core.client.creditcard.CreditCardClientMock;
import com.moip.core.client.creditcard.CreditCardClientMock.CreditResponseType;

@RestController
@RequestMapping("api/card-mock")
public class CardMockController {

	private final CreditCardClientMock creditCardClientMock;

	@Autowired
	public CardMockController(CreditCardClientMock creditCardClientMock) {
		this.creditCardClientMock = creditCardClientMock;
	}

	@PutMapping(path = "timeout")
	public ResponseEntity<?> timeoutMock() {
		creditCardClientMock.timeoutResponse();
		return ResponseEntity.ok().build();
	}

	@PutMapping(path = "approve")
	public ResponseEntity<?> approveMock() {
		creditCardClientMock.approveResponse();
		return ResponseEntity.ok().build();
	}

	@PutMapping(path = "reprove")
	public ResponseEntity<?> reproveMock() {
		creditCardClientMock.reproveResponse();
		return ResponseEntity.ok().build();
	}

	@GetMapping
	public ResponseEntity<CreditResponseType> getResponseType() {
		CreditResponseType responseType = creditCardClientMock.getCreditResponseType();
		return ResponseEntity.ok(responseType);
	}
}
