package com.gv.dpal.account.dto.account;

import lombok.*;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AccountDetailsDto {

    private String accountId;
    private String userId;
    private String fullName;
    private String eMail;
    private AccountStatusDto status;
    private AccountTypeDto accountType;
    private Instant createdAt;
}
