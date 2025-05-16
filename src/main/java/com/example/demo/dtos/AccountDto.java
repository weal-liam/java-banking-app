package com.example.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AccountDto {
    private String accountHolder;
    private int accountNumber;
    private String email;
    private Long phoneNumber;
    private Double accountBalance;
}
