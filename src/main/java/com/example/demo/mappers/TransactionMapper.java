package com.example.demo.mappers;

import com.example.demo.dtos.TransactionDto;
import com.example.demo.entities.TransactionEntity;
import com.example.demo.models.Transaction;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    TransactionDto toDto(TransactionEntity entity);
    TransactionEntity toEntity(TransactionDto dto);
    TransactionEntity toEntity(Transaction transaction);
    Transaction toTransaction(TransactionEntity entity);
    Transaction toTransaction(TransactionDto dto);
}