package com.example.demo.entities;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Document(collection="transactions")
public class TransactionEntity {
    private AccountEntity recipient;
    private int transactionId;
    private int accountNumber;
    private Double amount;
    private String type;
    private String status;
    private String timestamp;
}