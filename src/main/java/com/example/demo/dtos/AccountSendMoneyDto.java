package com.example.demo.dtos;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountSendMoneyDto {
    private String accountHolder;
    private int accountNumber;
    private Double amount;
}