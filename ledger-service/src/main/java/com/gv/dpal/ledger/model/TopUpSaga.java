package com.gv.dpal.ledger.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Currency;

@Entity
@Table(name = "t_top_up_saga")
public class TopUpSaga extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long sagaId;
    private String idempotencyKey;

    private String transactionType;
    private String status;

    private String sourceCardNumber;
    private String destinationAccountId;
    private String destinationWalletId;

    private BigDecimal amount;
    private Currency currency;

    private String riskStatus;
    private String ledgerStatus;
    private String reservationStatus;

    private String failureCode;
    private String failureReason;

    private String compensationId;

    private Instant completedAt;
}
