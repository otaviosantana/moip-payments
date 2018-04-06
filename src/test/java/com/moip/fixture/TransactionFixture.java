package com.moip.fixture;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.moip.core.model.Card;
import com.moip.core.model.Client;
import com.moip.core.model.Payment;
import com.moip.core.model.PaymentType;
import com.moip.core.model.Person;
import com.moip.core.model.Transaction;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;

public class TransactionFixture {

    private static final LocalDate CARD_EXPIRATION_DATE = LocalDate.of(3000, 1, 1);
    public static final String VALID_WITH_BOLETO = "valid-with-boleto";
    public static final String VALID_WITH_CARD = "valid-with-card";
    public static final String VALID_BUYER = "valid-buyer";
    public static final String INVALID_PAYMENT_WITH_INVALID_CARDNUMBER = "invalid-payment-with-invalid-cardnumber";
    public static final String PAYMENT_WITHOUT_CARD = "payment-without-card";
    public static final String INVALID_CARD_NUMBER = "invalid-card-number";
    public static final String VALID_CARD = "valid-card";
    public static final String VALID_CLIENT = "valid-client";
    public static final String VALID_PAYMENT_WITH_BOLETO = "valid-payment-with-boleto";
    public static final String VALID_PAYMENT_WITH_CARD = "valid-payment-with-card";
    public static final String INVALID_TRANSACTION_WITH_INVALID_CARDNUMBER = "invalid-transaction-with-invalid-cardnumber";

    public static void setup() {
        Fixture.of(Client.class).addTemplate(VALID_CLIENT, new Rule() {
            {
                add("id", UUID.randomUUID().toString());
            }
        });
        Fixture.of(Card.class).addTemplate(INVALID_CARD_NUMBER, new Rule() {
            {
                add("cardHolderName", "Card Holder Name");
                add("number", "9999158288437434");
                add("cardExpirationDate", CARD_EXPIRATION_DATE);
                add("cvv", "809");
            }
        });
        Fixture.of(Card.class).addTemplate(VALID_CARD, new Rule() {
            {
                add("cardHolderName", "Card Holder Name");
                add("number", "4556158288437433");
                add("cardExpirationDate", CARD_EXPIRATION_DATE);
                add("cvv", "809");
            }
        });
        Fixture.of(Payment.class).addTemplate(VALID_PAYMENT_WITH_BOLETO, new Rule() {
            {
                add("amount", new BigDecimal("12.12"));
                add("type", PaymentType.BOLETO);
            }
        });
        Fixture.of(Payment.class).addTemplate(VALID_PAYMENT_WITH_CARD, new Rule() {
            {
                add("amount", new BigDecimal("12.12"));
                add("type", PaymentType.CREDIT_CARD);
                add("card", one(Card.class, VALID_CARD));
            }
        });
        Fixture.of(Payment.class).addTemplate(INVALID_PAYMENT_WITH_INVALID_CARDNUMBER, new Rule() {
            {
                add("amount", new BigDecimal("12.12"));
                add("type", PaymentType.CREDIT_CARD);
                add("card", one(Card.class, INVALID_CARD_NUMBER));
            }
        });
        Fixture.of(Payment.class).addTemplate(PAYMENT_WITHOUT_CARD, new Rule() {
            {
                add("amount", new BigDecimal("12.12"));
                add("type", PaymentType.CREDIT_CARD);
                add("card", null);
            }
        });
        Fixture.of(Person.class).addTemplate(VALID_BUYER, new Rule() {
            {
                add("name", "Card Holder Name");
                add("email", "email@email.com");
                add("cpf", "45698093083");
            }
        });
        Fixture.of(Transaction.class).addTemplate(VALID_WITH_BOLETO, new Rule() {
            {
                add("client", one(Client.class, VALID_CLIENT));
                add("buyer", one(Person.class, VALID_BUYER));
                add("payment", one(Payment.class, VALID_PAYMENT_WITH_BOLETO));
            }
        });
        Fixture.of(Transaction.class).addTemplate(VALID_WITH_CARD, new Rule() {
            {
                add("client", one(Client.class, VALID_CLIENT));
                add("buyer", one(Person.class, VALID_BUYER));
                add("payment", one(Payment.class, VALID_PAYMENT_WITH_CARD));
            }
        });
        Fixture.of(Transaction.class).addTemplate(VALID_WITH_CARD, new Rule() {
            {
                add("client", one(Client.class, VALID_CLIENT));
                add("buyer", one(Person.class, VALID_BUYER));
                add("payment", one(Payment.class, VALID_PAYMENT_WITH_CARD));
            }
        });
        Fixture.of(Transaction.class).addTemplate(INVALID_TRANSACTION_WITH_INVALID_CARDNUMBER, new Rule() {
            {
                add("client", one(Client.class, VALID_CLIENT));
                add("buyer", one(Person.class, VALID_BUYER));
                add("payment", one(Payment.class, INVALID_PAYMENT_WITH_INVALID_CARDNUMBER));
            }
        });
    }
}
