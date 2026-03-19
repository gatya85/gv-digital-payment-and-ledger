package com.gv.dpal.payment.dto;

import java.math.BigDecimal;
import java.util.Currency;

public record CreatePaymentResponse(String cardNumber, String accountId, String userId, BigDecimal amount, Currency currency) {
}
