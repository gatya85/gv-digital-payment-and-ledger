package com.gv.dpal.wallet.dto;

import java.util.Currency;
import java.util.UUID;

public record CreateWalletRequest (UUID accountId, Currency currency){

}
