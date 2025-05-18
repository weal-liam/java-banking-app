package com.example.demo.controllers;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.TransactionDto;
import com.example.demo.mappers.TransactionMapper;
import com.example.demo.repositories.TransactionRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("accounts/{accountHolder}/transactions")
public class TransactionController {
    private final TransactionMapper transactionMapper;
    private final TransactionRepository transactionRepository;

    @GetMapping
    public List<TransactionDto> viewSpecificTransaction(
        @RequestParam(name = "sort", required = false, defaultValue = "timestamp") String sortBy,
		@RequestParam(name = "dir", required = false,defaultValue = "desc") String direction
    ){	
		Sort sort = direction.equalsIgnoreCase("desc")? Sort.by(sortBy).descending():Sort.by(sortBy).ascending();
		/*
			String[] sortFields = sortBy.split(',');
			String[] directions = direction.split(',');
			Sort.Order[] orders = new Sort.Order[sortFields.length];
			for(int i = 0;i < sortFields.length; i++){
				String dir = (i < directions.length)? directions[i] : directions[directions.length - 1];
				Orders[i] = dir.equalsIgnoreCase("desc")? 
							new Sort.Order(Sort.Direction.DESC,SortFields[i]): 
							new Sort.Order(Sort.Direction.ASC,sortFields[i])
			}
		*/
        return transactionRepository.findAll(sort/*orders(For multiple queries)*/).stream().map(transactionMapper::toDto).toList();
    }
}
