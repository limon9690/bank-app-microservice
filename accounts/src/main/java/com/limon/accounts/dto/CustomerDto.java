package com.limon.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        name = "Customer",
        description = "Schema to hold Customer and Account Information"
)
public class CustomerDto {

    @NotEmpty(message = "Name can't be empty.")
    @Size(min = 5, max = 30, message = "Length of name must be between 5 and 30.")
    @Schema(
            description = "Name of the customer",
            example = "John Doe"
    )
    private String name;

    @NotEmpty(message = "Email can't be empty.")
    @Email(message = "Valid email format is required.")
    @Schema(
            description = "Email of the customer",
            example = "JohnDoe@gmail.com"
    )
    private String email;

    @NotEmpty
    @Size(message = "Mobile number must be 10 digits long.")
    @Schema(
            description = "Mobile Number of the customer",
            example = "1234567890"
    )
    private String mobileNumber;

    @Schema(
            description = "Account details of the customer"
    )
    private AccountDto account;
}
