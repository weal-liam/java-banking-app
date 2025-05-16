package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.ArrayList;
import com.example.demo.entities.AccountEntity;
import com.example.demo.entities.TransactionEntity;
import com.example.demo.mappers.TransactionMapper;
import com.example.demo.repositories.AccountRepository;

@Component // Marks this class as a Spring-managed bean
@Data
@AllArgsConstructor
public class Account {

    private List<TransactionEntity> transactions;
    private String accountHolder;
    private int accountNumber;
    private Double accountBalance;
    private String email;
    private Long phoneNumber;
    private String pin;

    @Autowired
    private Transaction transaction; // Injected by Spring

    private TransactionMapper transactionMapper;
    private AccountRepository accountRepository;

    public Account() {
        // Default constructor for Spring
        transactions = new ArrayList<>();
    }

    public Account(String accountHolder, int accountNumber, Double accountBalance, String email, Long phoneNumber, String pin) {
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.pin = pin;
    }

    public void checkBalance() {
        System.out.println("Your current balance is " + accountBalance);
    }

    public void withdraw(Double withdrawal) {
                transaction = new Transaction(this.accountNumber, withdrawal, (int) Math.floor(1000000 * Math.random()), "withdraw", "inProgress");
                transaction.transact(withdrawal, this.accountHolder, this.accountNumber);
                Double balance = this.accountBalance - ((0.1 * withdrawal) + withdrawal);
                if (balance < 20000) {
                    System.out.println("Cannot complete withdraw due to low balance");
                    transaction.setStatus("failure");
                    this.transactions.add(transactionMapper.toEntity(transaction));
                } else {
                    System.out.println(this.accountHolder + this.accountNumber + " has withdrawn " + withdrawal + "\n"
                            + "You now have a balance of " + balance);
                    setAccountBalance(balance);
                    transaction.setStatus("success");
                    this.transactions.add(transactionMapper.toEntity(transaction));
                }
            }

    public void deposit(Double deposit) {
            transaction = new Transaction(this.accountNumber, deposit, (int) Math.floor(1000000 * Math.random()), "deposit", "inProgress");
            transaction.transact(deposit, this.accountHolder, this.accountNumber);
            setAccountBalance(this.accountBalance + deposit);
            System.out.println(this.accountHolder + this.accountNumber + " has made a deposit of " + deposit + "\n"
                    + "Your new account balance is " + this.accountBalance);
            transaction.setStatus("success");
            this.transactions.add(transactionMapper.toEntity(transaction));
        }

    public void sendMoney(List<AccountEntity> accounts, Double amount, String receiver, int receiverAccountNumber) {
            transaction = new Transaction(this.getAccountNumber(), amount, (int) Math.floor(1000000 * Math.random()), "transfer", "inProgress");
            transaction.transact(amount, this.getAccountHolder(), this.getAccountNumber());
            transaction.getReceiverAccount(accounts, receiver, receiverAccountNumber);
            AccountEntity recipient = transaction.getRecipient();
            Double tax = (Double) 0.1 * amount;
            Double balance = this.getAccountBalance() - (amount + tax);
            this.setAccountBalance(balance);
            Double gain = recipient.getAccountBalance() + amount;
            recipient.setAccountBalance(gain);
            System.out.println(this.getAccountHolder() + this.getAccountNumber() + " has sent " + amount + " to " + recipient.getAccountHolder()+ " at a fee of " + tax);
            transaction.setStatus("Success");
            this.transactions.add(transactionMapper.toEntity(transaction));
            recipient.getTransactions().add(transactionMapper.toEntity(transaction));
            accountRepository.save(recipient);
        }
}
