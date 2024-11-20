package com.limon.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        name = "Account",
        description = "Schema to hold Account Information"
)
public class AccountDto {
    @NotEmpty(message = "Account number can't be empty.")
    @Schema(
            description = "Account Number of the customer"
    )
    private Long accountNumber;

    @NotEmpty(message = "Account type can't be empty.")
    @Schema(
            description = "Account Type of the customer",
            example = "Savings"
    )
    private String accountType;

    @NotEmpty(message = "Branch Address type can't be empty.")
    @Schema(
            description = "Address of the account branchr",
            example = "24/7,North Street"
    )
    private String branchAddress;
}
