package com.gv.dpal.ledger.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Currency;

public record CreateTopUpSagaLedgerRequest(
        Long sagaId,
        String idempotencyKey,
        String transactionType,
        String status,
        String sourceCardNumber,
        String destinationAccountId,
        String destinationWalletId,
        BigDecimal amount,
        Currency currency,
        String riskStatus,
        String ledgerStatus,
        String reservationStatus,
        String failureCode,
        String failureReason,
        String compensationId,
        Instant completedAt
){}
