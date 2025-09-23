package com.evolting.accounts.mappers;

import com.evolting.accounts.dtos.CustomerDto;
import com.evolting.accounts.entities.Customer;

public class CustomerMapper {

    public static CustomerDto mapToCustomerDTO(Customer customer, CustomerDto customerDTO) {
        customerDTO.setName(customer.getName());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setMobileNumber(customer.getMobileNumber());
        return customerDTO;
    }

    public static Customer mapToCustomer(CustomerDto customerDTO, Customer customer) {
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        customer.setMobileNumber(customerDTO.getMobileNumber());
        return customer;
    }

}
