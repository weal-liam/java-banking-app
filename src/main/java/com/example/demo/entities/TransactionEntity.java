package com.example.demo.entities;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Document(collection="transactions")
public class TransactionEntity {
	//if null ignore
    private String recipient;
	//if null ignore
	private  int receiverAccountNumber;
	
    private int transactionId;
    private int accountNumber;
    private Double amount;
    private String type;
    private String status;
	//and timestamp or createdAt
    private String timestamp;
}