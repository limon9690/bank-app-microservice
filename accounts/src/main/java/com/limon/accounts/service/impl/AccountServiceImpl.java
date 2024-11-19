package com.limon.accounts.service.impl;

import com.limon.accounts.constants.AccountConstants;
import com.limon.accounts.dto.AccountDto;
import com.limon.accounts.dto.CustomerDto;
import com.limon.accounts.entity.Account;
import com.limon.accounts.entity.Customer;
import com.limon.accounts.exception.CustomerAlreadyExistsException;
import com.limon.accounts.exception.ResourceNotFoundException;
import com.limon.accounts.mapper.AccountMapper;
import com.limon.accounts.mapper.CustomerMapper;
import com.limon.accounts.repository.AccountRepository;
import com.limon.accounts.repository.CustomerRepository;
import com.limon.accounts.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumberOrEmail(customerDto.getMobileNumber(), customerDto.getEmail());

        if (optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer Already exists with given mobile No / Email");
        }

        Customer savedCustomer = customerRepository.save(customer);

        Account account = Account.builder()
                .customerId(savedCustomer.getCustomerId())
                .accountType(AccountConstants.SAVINGS)
                .branchAddress(AccountConstants.ADDRESS)
                .build();

      accountRepository.save(account);
    }

    @Override
    public CustomerDto fetchAccountDetails(String mobileNumber) {
        Optional<Customer> customer = customerRepository.findByMobileNumber(mobileNumber);

        if (!customer.isPresent()) {
            throw new ResourceNotFoundException("Customer", "mobile number", mobileNumber);
        }

        Optional<Account> account = Optional.ofNullable(accountRepository.findByCustomerId(customer.get().getCustomerId())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Account", "customerId", customer.get().getCustomerId().toString())
                ));

        AccountDto accountDto = AccountMapper.mapToAccountDto(account.get(), new AccountDto());
        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer.get(), new CustomerDto());
        customerDto.setAccount(accountDto);

        return customerDto;
    }

    @Override
    @Transactional
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;
        AccountDto accountDto = customerDto.getAccount();

        if (accountDto != null) {
            Account account = accountRepository.findById(accountDto.getAccountNumber())
                    .orElseThrow(() -> new ResourceNotFoundException("Account", "accountId", accountDto.getAccountNumber().toString()));

            AccountMapper.mapToAccount(accountDto, account);
            account = accountRepository.save(account);

            Long customerId = account.getCustomerId();
            Customer customer = customerRepository.findById(customerId)
                    .orElseThrow(() -> new ResourceNotFoundException("Customer", "customerId", customerId.toString()));

            CustomerMapper.mapToCustomer(customerDto, customer);
            customerRepository.save(customer);
            isUpdated = true;
        }

        return isUpdated;
    }

    @Override
    @Transactional
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Custoemr", "mobileNo", mobileNumber));

        accountRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());

        return true;
    }


}
