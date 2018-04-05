package com.moip.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.moip.core.client.creditcard.CreditCardClient;
import com.moip.core.client.creditcard.CreditCardClient.CreditCardPaymentResponse;
import com.moip.core.model.Card;
import com.moip.core.model.Payment;
import com.moip.core.model.PaymentResponse;
import com.moip.core.model.PaymentType;
import com.moip.core.model.Transaction;
import com.moip.core.repository.TransactionRepository;

import br.com.moip.validators.CreditCard;

@Component
public class CreditCardTransactionStrategy implements TransactionStrategy {

    private TransactionRepository transactionRepository;
    private CreditCardClient creditCardClient;

    @Autowired
    public CreditCardTransactionStrategy(TransactionRepository transactionRepository, CreditCardClient creditCardClient) {
        this.transactionRepository = transactionRepository;
        this.creditCardClient = creditCardClient;
    }

    @Override
    public PaymentResponse processPayment(Transaction transaction) {
        Payment payment = transaction.getPayment();
        if (payment.getType() == PaymentType.BOLETO) {
            throw new IllegalArgumentException("This method can't be called for boleto payments");
        }
        Card card = payment.getCard();
        if (card == null) {
            throw new IllegalArgumentException("Card can't be null");
        }
        if (!new CreditCard(card.getNumber()).isValid()) {
            throw new IllegalArgumentException("Invalid card number");
        }
        transactionRepository.save(transaction);
        CreditCardPaymentResponse paymentResponse = creditCardClient.newPayment(payment.getAmount(), payment.getCard());
        payment.setStatus(paymentResponse.getPaymentStatus());
        card.setAuthorizationCode(paymentResponse.getAuthorizationCode());
        transactionRepository.save(transaction);
        return new PaymentResponse(paymentResponse.getPaymentStatus(), null);
    }
}
