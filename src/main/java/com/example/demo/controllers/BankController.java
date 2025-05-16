package com.example.demo.controllers;

import com.example.demo.models.Bank;
import com.example.demo.dtos.AccountDto;
import com.example.demo.dtos.AccountRequestDto;
import com.example.demo.entities.AccountEntity;
import com.example.demo.mappers.AccountMapper;
import com.example.demo.models.Account;
import com.example.demo.repositories.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/bankApi/v1")
public class BankController {
    private final Bank bankServer;
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, Banking World!";
    }

    @GetMapping("/accounts")
    public List<AccountDto> revealAccounts() {
        return accountRepository.findAll()
                .stream()
                .map(accountMapper::toDto)
                .toList();
    }

    @GetMapping("/accounts/{accountHolder}")
    public AccountDto getAccount(@PathVariable String accountHolder) {
        AccountEntity entity = accountRepository.findByAccountHolder(accountHolder);
        return accountMapper.toDto(entity);
    }

     @PostMapping("/accounts")
    public List<AccountDto> createAccount(@RequestBody AccountRequestDto request){
        Account account = accountMapper.toAccount(request);
        bankServer.createAccount(
                account.getAccountHolder(),
                account.getAccountBalance(),
                account.getEmail(),
                account.getPhoneNumber(),
                account.getPin()
        );
        AccountEntity entity = bankServer.getAccounts().stream()
                .filter(accountEntity -> accountEntity.getAccountHolder().equals(account.getAccountHolder()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Account not found"));
        accountRepository.save(entity);
        return accountRepository.findAll()
                .stream()
                .map(accountMapper::toDto)
                .toList();
    }
}


