package com.gv.dpal.account.dto.account;

import com.gv.dpal.account.model.AccountStatus;
import com.gv.dpal.account.model.AccountType;

import java.time.Instant;
import java.util.UUID;

public record CreateAccountResponse(UUID accountId,
                                    String userId,
                                    String fullName,
                                    String eMail,
                                    AccountStatus status,
                                    AccountType type,
                                    Instant createdAt,
                                    Instant updatedAt) {
}
