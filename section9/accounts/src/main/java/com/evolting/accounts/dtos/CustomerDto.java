package com.evolting.accounts.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    @NotEmpty(message = "Name is mandatory")
    @Size(min = 3, max = 30, message = "Name should be between 3 and 30 characters")
    private String name;

    @NotEmpty(message = "Email is mandatory")
    @Email(message = "Please provide a valid email address")
    private String email;

    @Pattern(regexp = "^[0-9]{10}$", message = "Please provide a valid mobile number")
    private String mobileNumber;

    private AccountsDto accountsDTO;
}
