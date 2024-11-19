package com.limon.accounts.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    @NotEmpty(message = "Account number can't be empty.")
    private Long accountNumber;

    @NotEmpty(message = "Account type can't be empty.")
    private String accountType;

    @NotEmpty(message = "Branch Address type can't be empty.")
    private String branchAddress;
}
