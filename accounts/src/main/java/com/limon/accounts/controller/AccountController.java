package com.limon.accounts.controller;

import com.limon.accounts.constants.AccountConstants;
import com.limon.accounts.dto.CustomerDto;
import com.limon.accounts.dto.ErrorResponseDto;
import com.limon.accounts.dto.ResponseDto;
import com.limon.accounts.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(
        name = "CRUD REST APIs for Account in E-Bank",
        description = "CRUD REST APIs in E-Bank to CREATE, READ, UPDATE, FETCH & DELETE"
)
public class AccountController {
    private final AccountService accountService;

    @Operation(
            summary = "Create Account REST API",
            description = "REST API to create new Customer & Account"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Http Status - CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Http Status - INTERNAL SERVER ERROR",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
        accountService.createAccount(customerDto);
        return new ResponseEntity<>(new ResponseDto(AccountConstants.STATUS_201, AccountConstants.MESSAGE_201), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Fetch Account Details REST API",
            description = "REST API to fetch details of a Customer Based On Mobile Number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam @Size(message = "Mobile number must be 10 digits long.") String mobileNumber) {
        CustomerDto customerDto = accountService.fetchAccountDetails(mobileNumber);
        return new ResponseEntity<>(customerDto, HttpStatus.OK);
    }

    @Operation(
            summary = "Update Account REST API",
            description = "REST API to update a Customer's Account"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto) {
        boolean isUpdated = accountService.updateAccount(customerDto);

        if (isUpdated) {
            return new ResponseEntity<>(new ResponseDto(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseDto(AccountConstants.STATUS_500, AccountConstants.MESSAGE_500), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(
            summary = "Delete Account REST API",
            description = "REST API to delete a Customer's Account and Details Based On Mobile Number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
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
