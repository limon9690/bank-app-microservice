package com.limon.accounts.repository;

import com.limon.accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByMobileNumberOrEmail(String mobileNumber, String email);
    Optional<Customer> findByMobileNumber(String mobileNumer);
}
