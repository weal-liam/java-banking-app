package com.example.demo.controllers;

import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.AllArgsConstructor;

import com.example.demo.mappers.AccountMapper;
import com.example.demo.dtos.*;
import com.example.demo.entities.*;
import com.example.demo.mappers.TransactionMapper;
import com.example.demo.models.Account;
import com.example.demo.repositories.*;
import com.example.demo.dtos.AccountSendMoneyDto;

@AllArgsConstructor
@RestController
@RequestMapping("/accounts/{accountHolder}")
public class AccountController {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final AccountMapper accountMapper;
    private final TransactionMapper transactionMapper;
    @PatchMapping("/deposit")
    public AccountDto makeDeposit(
    @RequestBody Double deposit,@PathVariable String accountHolder) {
        AccountEntity entity = accountRepository.findByAccountHolder(accountHolder);
        Account account = accountMapper.toAccount(entity);
        account.deposit(deposit);
        entity.setAccountBalance(account.getAccountBalance());
        accountRepository.save(entity);
        return accountMapper.toDto(entity);
    }

    @PatchMapping("/withdraw")
    public AccountDto makeWithdraw(
    @RequestBody Double withdrawal,@PathVariable String  accountHolder){
        AccountEntity entity = accountRepository.findByAccountHolder(accountHolder);
        Account account = accountMapper.toAccount(entity);
        account.withdraw(withdrawal);
        entity.setAccountBalance(account.getAccountBalance());
        accountRepository.save(entity);
        return accountMapper.toDto(entity);
    }

    @PatchMapping("/send-money")
    public TransactionDto sendMoney(
    @RequestBody AccountSendMoneyDto receiver,@PathVariable String accountHolder){
        AccountEntity entity = accountRepository.findByAccountHolder(accountHolder);
        List<AccountEntity> accounts = accountRepository.findAll();
        Account account = accountMapper.toAccount(entity);
        account.sendMoney(accounts,receiver.getAmount(),receiver.getAccountHolder(),receiver.getAccountNumber());
        entity.setAccountBalance(account.getAccountBalance());
        accountRepository.save(entity);
        TransactionEntity transaction = transactionRepository.findByAccountNumber(account.getAccountNumber());
        return transactionMapper.toDto(transaction);
    }
    
}
