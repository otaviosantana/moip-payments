package com.moip.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moip.core.model.Client;
import com.moip.core.model.PaymentResponse;
import com.moip.core.model.Transaction;
import com.moip.core.repository.TransactionRepository;
import com.moip.core.service.TransactionStrategy;
import com.moip.core.service.TransactionStrategyFactory;

@RestController
@RequestMapping("api/clients/{clientId}/transactions")
public class TransactionsController {

    @Autowired
    private TransactionStrategyFactory transactionStrategyFactory;
    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping
    public ResponseEntity<List<Transaction>> getTransactions(@PathVariable("clientId") String clientId) {
        List<Transaction> transactions = transactionRepository.findAllByClientId(clientId);
        return ResponseEntity.ok(transactions);
    }
    
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getTransactionById(@PathVariable("clientId") String clientId, @PathVariable(name = "id") String id) {
        Optional<Transaction> transaction = transactionRepository.findByIdAndClientId(id, clientId);
        return ResponseEntity.ok(transaction.orElse(null)); 
    }

    @PostMapping
    public ResponseEntity<PaymentResponse> processTransaction(@PathVariable("clientId") String clientId, @RequestBody @Valid Transaction transaction) {
        Client client = new Client(clientId);
        transaction.setClient(client);
        TransactionStrategy strategy = transactionStrategyFactory.getStrategy(transaction.getPayment().getType());
        PaymentResponse paymentResponse = strategy.processPayment(transaction);
        return ResponseEntity.ok(paymentResponse);
    }
}
