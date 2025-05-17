package com.example.demo.dtos;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountTransactMoneyDto {
    private String accountHolder;
    private int accountNumber;
    private Double amount;
	private String pin;
}