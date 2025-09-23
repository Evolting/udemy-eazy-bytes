package com.evolting.accounts.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountsDto {
    @NotEmpty(message = "Account Number is mandatory")
    @Pattern(regexp = "^[0-9]{10}$", message = "Account number should be 10 digits")
    private Long accountNumber;

    @NotEmpty(message = "Account Type is mandatory")
    private String accountType;

    @NotEmpty(message = "Branch Address is mandatory")
    private String branchAddress;
}
