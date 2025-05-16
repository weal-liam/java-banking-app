package com.example.demo.mappers;

import com.example.demo.dtos.AccountDto;
import com.example.demo.dtos.AccountRequestDto;
import com.example.demo.dtos.AccountUpdateDto;
import com.example.demo.models.Account;
import com.example.demo.entities.AccountEntity;

import org.mapstruct.*;


@Mapper(componentModel = "spring")
public interface AccountMapper {
    Account toAccount(AccountEntity entity);
    Account toAccount(AccountRequestDto dto);
    AccountDto toDto(AccountEntity account);
    AccountDto toAccountDto(AccountRequestDto dto);
    void toEntity(AccountUpdateDto dto, @MappingTarget AccountEntity entity);
    void toEntity(AccountDto dto, @MappingTarget AccountEntity entity);
    AccountEntity toEntity(AccountRequestDto dto);
    AccountEntity toEntity(Account account);
}
