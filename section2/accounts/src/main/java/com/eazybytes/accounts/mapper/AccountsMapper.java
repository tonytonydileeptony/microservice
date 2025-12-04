package com.eazybytes.accounts.mapper;

import com.eazybytes.accounts.dto.AccountsDto;
import com.eazybytes.accounts.entity.Accounts;

public class AccountsMapper {
    public static AccountsDto maptToAccountsDto(Accounts accounts, AccountsDto accountDto){
        accountDto.setAccountNumber((accounts.getAccountNumber()));
        accountDto.setAccountType(accounts.getAccountType());
        accountDto.setBranchAddress(accounts.getBranchAddress());
        return accountDto;
    }
    public static Accounts maptToAccounts( AccountsDto accountDto,Accounts accounts){
        accounts.setAccountNumber((accountDto.getAccountNumber()));
        accounts.setAccountType(accountDto.getAccountType());
        accounts.setBranchAddress(accountDto.getBranchAddress());
        return accounts;
    }
}
