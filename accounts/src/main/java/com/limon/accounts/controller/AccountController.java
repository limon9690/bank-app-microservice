package com.limon.accounts.controller;

import com.limon.accounts.constants.AccountConstants;
import com.limon.accounts.dto.CustomerDto;
import com.limon.accounts.dto.ResponseDto;
import com.limon.accounts.service.AccountService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Validated
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
        accountService.createAccount(customerDto);
        return new ResponseEntity<>(new ResponseDto(AccountConstants.STATUS_201, AccountConstants.MESSAGE_201), HttpStatus.CREATED);
    }

    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam @Size(message = "Mobile number must be 10 digits long.") String mobileNumber) {
        CustomerDto customerDto = accountService.fetchAccountDetails(mobileNumber);
        return new ResponseEntity<>(customerDto, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto) {
        boolean isUpdated = accountService.updateAccount(customerDto);

        if (isUpdated) {
            return new ResponseEntity<>(new ResponseDto(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseDto(AccountConstants.STATUS_500, AccountConstants.MESSAGE_500), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> DeleteAccountDetails(@RequestParam @Size(message = "Mobile number must be 10 digits long.") String mobileNumber) {
        boolean isDeleted = accountService.deleteAccount(mobileNumber);

        if (isDeleted) {
            return new ResponseEntity<>(new ResponseDto(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseDto(AccountConstants.STATUS_500, AccountConstants.MESSAGE_500), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
