package com.example.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class TransactionDto {
	private int transactionId;
	private int myAccountNumber;
	//ignore if null
    private String recipient;
	//ignore if null
	private int receiverAccountNumber;
	
    private Double amount;
    private String type;
    private String status;
	//include timestamp or created at
	private String timestamp;
}
