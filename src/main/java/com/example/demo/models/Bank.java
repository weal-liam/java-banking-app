package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;
import com.example.demo.entities.AccountEntity;

@Service // Marks this class as a Spring-managed bean
@Data
@AllArgsConstructor
public class Bank {

    private List<AccountEntity> accounts;
    private List<AccountEntity> activeAccounts;

    @Autowired
    private Transaction transaction; // Injected by Spring

    public Bank() {
        accounts = new ArrayList<>();
        activeAccounts = new ArrayList<>(); // Initialize the queue
    }

    public void createAccount(String accountHolder, Double accountBalance, String email, Long phoneNumber, String  pin) {
        if (accountHolder.length() < 1) 
                System.out.println("Invalid account holder name!");
        if (accountBalance <= 20000) 
                System.out.println("Amount too small!");

        AccountEntity newAccount = new AccountEntity(accountHolder, (int) Math.floor(1000000 * Math.random()), accountBalance, email, phoneNumber, pin,null);

        accounts.add(newAccount);
        System.out.println("You have successfully been registered! \nThank you for choosing our services.");
    }

    public void setReceipient(String receiver, int accountNumber) {
        transaction.getReceiverAccount(accounts, receiver, accountNumber);
    }
}
