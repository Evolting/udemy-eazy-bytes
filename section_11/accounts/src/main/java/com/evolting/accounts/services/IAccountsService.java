package com.evolting.accounts.services;

import com.evolting.accounts.dtos.CustomerDto;

public interface IAccountsService {

    void createAccount(CustomerDto customerDto);

    CustomerDto fetchAccount(String mobileNumber);

    boolean updateAccount(CustomerDto customerDTO);

    boolean deleteAccount(String mobileNumber);
}
