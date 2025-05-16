package com.example.demo.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.TransactionDto;
import com.example.demo.mappers.TransactionMapper;
import com.example.demo.repositories.TransactionRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionMapper transactionMapper;
    private final TransactionRepository transactionRepository;

    @GetMapping
    public List<TransactionDto> viewSpecificTransaction(
        @RequestParam(name = "sort", required = false) String sortBy
    ){
        return transactionRepository.findAll().stream().map(transactionMapper::toDto).toList();
    }
}
