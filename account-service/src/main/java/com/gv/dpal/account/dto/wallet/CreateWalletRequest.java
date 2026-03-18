package com.gv.dpal.account.dto.wallet;

import java.util.Currency;
import java.util.UUID;

public record CreateWalletRequest (UUID accountId, Currency currency){

}