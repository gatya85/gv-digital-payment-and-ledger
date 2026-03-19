package com.gv.dpal.account.dto.account;

import java.time.Instant;
import java.util.UUID;

public record AccountDetailsResponse(UUID accountId,
                                     String userId,
                                     String fullName,
                                     String eMail,
                                     AccountStatus status,
                                     AccountType accountType,
                                     Instant createdAt,
                                     Instant updatedAt) {


}
