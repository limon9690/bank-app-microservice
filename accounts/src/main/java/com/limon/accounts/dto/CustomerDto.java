package com.limon.accounts.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    @NotEmpty(message = "Name can't be empty.")
    @Size(min = 5, max = 30, message = "Length of name must be between 5 and 30.")
    private String name;

    @NotEmpty(message = "Email can't be empty.")
    @Email(message = "Valid email format is required.")
    private String email;

    @NotEmpty
    @Size(message = "Mobile number must be 10 digits long.")
    private String mobileNumber;

    private AccountDto account;
}
