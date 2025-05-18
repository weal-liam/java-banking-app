package com.example.demo.models;


import lombok.AllArgsConstructor;
import lombok.Data;

import org.springframework.stereotype.Component;

import java.util.List;
import com.example.demo.entities.AccountEntity;

@Component // Marks this class as a Spring-managed bean
@Data
@AllArgsConstructor
public class Transaction {

    private int transactionId;
    private int accountNumber;
    private Double amount;
    private String type;
    private String status;
	//private Date CreatedAt;

    // Default constructor for Spring
    public Transaction() {
    }

    public Transaction(int accountNumber, Double amount, int transactionId, String type, String status/*,Date date*/) {
		this.accountNumber = accountNumber;
        this.amount = amount;
        this.transactionId = transactionId;
        this.type = type;
        this.status = status;
		//this.createdAt = date;
    }


    public void transact(Double amount, String transactor, int userId) {
        System.out.println("Transaction Id: " + transactionId + "\n" + transactor + userId + " is transacting an amount of " + amount);
    }
}
