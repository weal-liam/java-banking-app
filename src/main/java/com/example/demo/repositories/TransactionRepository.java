package com.example.demo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.entities.TransactionEntity;

public interface TransactionRepository extends MongoRepository<TransactionEntity,String> {
    TransactionEntity findByTransactionId(int transactionId);
    TransactionEntity findByAccountNumber(int accountNumber);
    TransactionEntity findByStatus(String transactionStatus);
    TransactionEntity findByType(String transactionType);
    TransactionEntity findByTimestamp(String timeStamp);
    TransactionEntity findByAmount(Double transactionAmount);
}