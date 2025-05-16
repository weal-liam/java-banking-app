package com.example.demo.entities;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Document(collection = "accounts")
@Data
@AllArgsConstructor
public class AccountEntity {
    private String accountHolder;
    private int accountNumber;
    private Double accountBalance;
    private String email;
    private Long phoneNumber;
    private String pin;
    private List<TransactionEntity> transactions;
}