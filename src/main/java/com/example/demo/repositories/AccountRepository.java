package com.example.demo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.entities.AccountEntity;

public interface AccountRepository extends MongoRepository<AccountEntity,String> {
    AccountEntity findByAccountHolder(String accountHolder);
    AccountEntity findByAccountNumber(String accountNumber);
    AccountEntity findByAccountHolderAndAccountNumber(String accountHolder, String accountNumber);
}
