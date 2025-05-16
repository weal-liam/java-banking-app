package com.example.demo.dtos;

import com.example.demo.models.Account;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class TransactionDto {
    private Account recipient;
    private int transactionId;
    private int accountNumber;
    private Double amount;
    private String type;
    private String status;
}
