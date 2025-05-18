package com.example.demo.controllers;

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
import com.example.demo.dtos.AccountTransactMoneyDto;

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
    @RequestBody AccountTransactMoneyDto dto,@PathVariable String accountHolder) {
        AccountEntity entity = accountRepository.findByAccountHolder(accountHolder);
        Account account = accountMapper.toAccount(entity);
        account.deposit(dto.getAmount());
        entity.setAccountBalance(account.getAccountBalance());
        accountRepository.save(entity);
        return accountMapper.toDto(entity);
    }

    @PatchMapping("/withdraw")
    public AccountDto makeWithdraw(
    @RequestBody AccountTransactMoneyDto dto,@PathVariable String  accountHolder){
        AccountEntity entity = accountRepository.findByAccountHolder(accountHolder);
        Account account = accountMapper.toAccount(entity);
        account.withdraw(dto.getAmount());
        entity.setAccountBalance(account.getAccountBalance());
        accountRepository.save(entity);
        return accountMapper.toDto(entity);
    }

    @PatchMapping("/send-money")
    public TransactionDto sendMoney(
    @RequestBody AccountTransactMoneyDto receiver,@PathVariable String accountHolder){
        AccountEntity entity = accountRepository.findByAccountHolder(accountHolder);
        AccountEntity recipient = accountRepository.findByAccountHolder(receiver.getAccountHolder());
        Account account = accountMapper.toAccount(entity);
		//database transaction start
        account.sendMoney(recipient,receiver.getAmount());
        entity.setAccountBalance(account.getAccountBalance());
		//database transaction stop
        accountRepository.save(entity);
		accountRepository.save(recipient);
		TransactionEntity transaction = account.getTransactions().get(account.getTransactions().size()-1);
		transactionRepository.save(transaction);
        return transactionMapper.toDto(transaction);
    }
    
}
