package com.gv.dpal.wallet.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Currency;
import java.util.UUID;

public record TopUpWalletResponse(UUID walletId,
                                  UUID accountId,
                                  Currency currency,
                                  BigDecimal availableBalance,
                                  BigDecimal reservedBalance,
                                  Instant createdAt,
                                  Instant updatedAt){
}
