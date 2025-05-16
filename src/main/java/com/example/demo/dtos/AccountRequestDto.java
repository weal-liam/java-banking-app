package com.example.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AccountRequestDto {
    private String accountHolder;
    private String email;
    private Long phoneNumber;
    //@JsonIgnore
    private int accountNumber;
    //@JsonIgnore
    private Double accountBalance;
    //@JsonIgnore
    private String pin;
}