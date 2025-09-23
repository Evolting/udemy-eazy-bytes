package com.evolting.accounts.services;

import com.evolting.accounts.dtos.CustomerDetailsDto;

public interface ICustomersService {

    CustomerDetailsDto fetchCustomerDetails(String mobileNumber);

}
