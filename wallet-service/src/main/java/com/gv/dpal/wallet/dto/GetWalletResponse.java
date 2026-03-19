package com.gv.dpal.wallet.dto;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.UUID;

public record GetWalletResponse (UUID walletId, UUID accountId, Currency currency, BigDecimal availableBalance, BigDecimal reservedBalance){
}
