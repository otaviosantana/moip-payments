package com.moip.core.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.moip.core.model.Transaction;

public interface TransactionRepository extends MongoRepository<Transaction, String> {

    Optional<Transaction> findByIdAndClientId(String id, String clientId);

    List<Transaction> findAllByClientId(String clientId);
}
