package com.gv.dpal.wallet.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record TopUpWalletRequest (UUID walletId, BigDecimal amount){
}
