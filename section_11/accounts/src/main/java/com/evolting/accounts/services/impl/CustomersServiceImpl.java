package com.evolting.accounts.services.impl;

import com.evolting.accounts.dtos.AccountsDto;
import com.evolting.accounts.dtos.CardsDto;
import com.evolting.accounts.dtos.CustomerDetailsDto;
import com.evolting.accounts.dtos.LoansDto;
import com.evolting.accounts.entities.Accounts;
import com.evolting.accounts.entities.Customer;
import com.evolting.accounts.exceptions.ResourceNotFoundException;
import com.evolting.accounts.mappers.AccountsMapper;
import com.evolting.accounts.mappers.CustomerMapper;
import com.evolting.accounts.repositories.AccountsRepository;
import com.evolting.accounts.repositories.CustomerRepository;
import com.evolting.accounts.services.ICustomersService;
import com.evolting.accounts.services.client.CardsFeignClient;
import com.evolting.accounts.services.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomersServiceImpl implements ICustomersService {
    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    private CardsFeignClient cardsFeignClient;
    private LoansFeignClient loansFeignClient;

    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );

        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

        ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchLoanDetails(mobileNumber);
        if(loansDtoResponseEntity != null) {
            customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());
        }

        ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails(mobileNumber);
        if(cardsDtoResponseEntity != null) {
            customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());
        }

        return customerDetailsDto;

    }
}
