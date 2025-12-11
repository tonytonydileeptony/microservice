package com.eazybytes.accounts.service.impl;

import com.eazybytes.accounts.constants.AccountsConstants;
import com.eazybytes.accounts.dto.AccountsDto;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.entity.Accounts;
import com.eazybytes.accounts.entity.Customer;
import com.eazybytes.accounts.exception.CustomerAlreadyExistsException;
import com.eazybytes.accounts.exception.ResourceNotFoundException;
import com.eazybytes.accounts.mapper.AccountsMapper;
import com.eazybytes.accounts.mapper.CustomerMapper;
import com.eazybytes.accounts.repository.AccountsRepository;
import com.eazybytes.accounts.repository.CustomerRespository;
import com.eazybytes.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {
    private AccountsRepository accountsRepository;
    private CustomerRespository customerRespository;

    @Override
    public void createAccount(CustomerDto customerDto) {
        Optional<Customer> optionalCustomer=customerRespository.findByMobileNumber(customerDto.getMobileNumber());
        if(optionalCustomer.isPresent()){
            throw new CustomerAlreadyExistsException("Customer with mobile number already exist"+customerDto.getMobileNumber());
        }
        Customer customer= CustomerMapper.mapToCustomer(customerDto,new Customer());
        customer.setCreatedAt(LocalDate.now());
        customer.setCreatedBy("Annonomus");
        Customer savedCustomer=customerRespository.save(customer);
        savedCustomer.setCreatedAt(LocalDate.now());
        savedCustomer.setCreatedBy("Annonomus");
        accountsRepository.save(createNewAccount(savedCustomer));
    }

    @Override
    public CustomerDto fetchAccountDetails(String number) {
       Customer customer= customerRespository.findByMobileNumber(number).orElseThrow(()->new ResourceNotFoundException("Customer","mobilenumber",number));
        Accounts accounts= accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(()->new ResourceNotFoundException("Accounts","customer id",customer.getCustomerId().toString()));
     CustomerDto customerDto= CustomerMapper.mapToCustomerDto(customer,new CustomerDto());
     customerDto.setAccountsDto(AccountsMapper.maptToAccountsDto(accounts,new AccountsDto()));
    return customerDto;
    }

    private Accounts createNewAccount(Customer customer){
        Accounts newAccount=new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber=1000000000L+new Random().nextInt(900000000);
        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setCreatedAt(LocalDate.now());
        newAccount.setCreatedBy("Annonomous");
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        return newAccount;
    }
}
