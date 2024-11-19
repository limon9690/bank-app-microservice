package com.limon.accounts.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "accounts")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account extends BaseEntitiy{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long accountNumber;

    private String accountType;

    private String branchAddress;

    private Long customerId;

}
