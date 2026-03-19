package com.gv.dpal.payment.dto;

import java.math.BigDecimal;
import java.util.Currency;

public record CreatePaymentRequest (String cardNumber, String accountId, String walletId, String userId, BigDecimal amount, Currency currency) {
}
